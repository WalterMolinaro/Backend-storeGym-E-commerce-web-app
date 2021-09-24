package com.ecommerce.storegym.services;

import com.ecommerce.storegym.entities.Carrello;
import com.ecommerce.storegym.entities.Ordine;
import com.ecommerce.storegym.entities.Prodotto;
import com.ecommerce.storegym.entities.ProdottoInOrdine;
import com.ecommerce.storegym.repositories.OrdineRepository;
import com.ecommerce.storegym.repositories.ProdottoInOrdineRepository;
import com.ecommerce.storegym.repositories.ProdottoRepository;
import com.ecommerce.storegym.repositories.UtenteRepository;
import com.ecommerce.storegym.support.exceptions.QuantitaProdottoNonDisponibile;
import com.ecommerce.storegym.support.exceptions.UtenteNonTrovatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.exit;

@Service
public class OrdineService {

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private ProdottoInOrdineRepository prodottoInOrdineRepository;

    @Autowired
    private ProdottoRepository prodottoRepository;

    @Autowired
    private UtenteRepository utenteRepository;


    /**
     * Aggiunge un ordine.
     * Il metodo sottostante consente di eliminare la persistenza di quella
     * che prima era l'entità carrello, per il nostro utente,
     * per generare un ordine costituito da una lista di <ProdottoOrdine>.
     * Naturamlmente quest'utlima entità andrà creata.
     * Se la quantità del prodotto presente nel db dovesse essere inferiore a quella richiesta per ogni singolo
     * prodottoOrdine, viene negato l'inserimento dell'ordine, sollevando l'eccezione QuantitaNonDisponibileException
     */
    @Transactional(readOnly = false)
    public Ordine aggiungiOrdine(Ordine ordine) throws QuantitaProdottoNonDisponibile{

        Ordine risultato = ordineRepository.save(ordine);
        if (risultato.getProdottiInOrdine() == null) risultato.setProdottiInOrdine(new ArrayList<>());
        for (ProdottoInOrdine prodottoInOrdine : risultato.getProdottiInOrdine()) {
            ProdottoInOrdine pio = prodottoInOrdine;
            try {
                pio = prodottoInOrdineRepository.save(prodottoInOrdine); //salvo ogni prodotto( che prima era nel carrello) nel db (creandolo anche);
            }catch(Exception es){
                System.out.println("Il salvataggio del prodotto in ordine non è andato a buon fine: "+ es.getMessage());
            }
            pio.setOrdine(risultato);
            Prodotto prodotto = prodottoRepository.findByProdottoId(pio.getProdotto().getProdottoId());
            if( prodotto == null ) {
                System.out.println("Prodotto non esistente");
                exit(-1);
            }
            if (prodotto.getProdottiInOrdine() == null) {
                prodotto.setProdottiInOrdine(new ArrayList<>());
            }
            prodotto.getProdottiInOrdine().add(pio);

            int nuovaQuantita = prodotto.getQuantitaTotale() - pio.getQuantitaRichiesta();
            if (nuovaQuantita < 0) {
                throw new QuantitaProdottoNonDisponibile();
            }
            prodotto.setQuantitaTotale(nuovaQuantita);
            pio.setProdotto(prodotto);

        }
        Carrello car = risultato.getUtente().getCarrello();
        car.setProdottiCarrello(new ArrayList<>());
        return risultato;
    }

    /**
     * Ottiene tutti gli ordini dell'utente con id passato da parametro
     */
    @Transactional(readOnly = true)
    public List<Ordine> getOrdiniDiUtente(Long idUtente) throws UtenteNonTrovatoException {
        if (!utenteRepository.existsById(idUtente)) {
            throw new UtenteNonTrovatoException();
        }
        return ordineRepository.findByUtente_IdUtente(idUtente);
    }

    /**
     * Ottiene un ordine specifico attraverso data di creazione e id utente
     */
    @Transactional(readOnly = true)
    public Ordine getOrdineSpecificoUtente(Long idUtente, String dataCreazione) {
        return ordineRepository.findByUtente_IdUtenteAndDataCreazione(idUtente,dataCreazione);
    }

}


