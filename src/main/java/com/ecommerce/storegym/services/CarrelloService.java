package com.ecommerce.storegym.services;

import com.ecommerce.storegym.entities.Carrello;
import com.ecommerce.storegym.entities.Prodotto;

import com.ecommerce.storegym.entities.ProdottoCarrello;
import com.ecommerce.storegym.entities.Utente;
import com.ecommerce.storegym.repositories.CarrelloRepository;
import com.ecommerce.storegym.repositories.ProdottoCarrelloRepository;
import com.ecommerce.storegym.repositories.UtenteRepository;
import com.ecommerce.storegym.support.exceptions.QuantitaProdottoNonDisponibile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarrelloService {

    @Autowired
    private CarrelloRepository carrelloRepository;

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private ProdottoCarrelloRepository prodottoCarrelloRepository;

    @Autowired
    private EntityManager entityManager;

    /**
     * Mostra tutti i prodotti dell'utente associato all'email ricevuta come parametro
     * Non esistono più utenti nel db con la stessa email.
     */
    @Transactional(readOnly = true)
    public List<ProdottoCarrello> mostraTuttiProdottiInCarrello(String email) {
        Utente ut = utenteRepository.findByEmail(email);

        Carrello c = ut.getCarrello();
        List<ProdottoCarrello> risultato = c.getProdottiCarrello();

        return (risultato == null) ? new ArrayList<>() : risultato;
    }


    /**
     *Aggiunge prodotto nel carrello, l'attenzione è stata posta sulla creazione del carrello
     * e sull'appoggio successivo ai metodo setter che hanno permesso di popolare l'entità
     */
    @Transactional(readOnly = false)
    public Carrello aggiungiProdotto(Long idCarrello, Prodotto prodotto, int quantitaRichiesta) throws QuantitaProdottoNonDisponibile {
        Carrello carrello = carrelloRepository.findCarrelloByCarrelloId(idCarrello);
        if( prodotto.getProdottiCarrelloLista() == null ) {
            prodotto.setProdottiCarrelloLista(new ArrayList<>());
        }
        ProdottoCarrello pcar = new ProdottoCarrello();
        pcar.setCarrello(carrello);
        pcar.setQuantitaRichiesta(quantitaRichiesta);
        pcar.setProdotto(prodotto);
        try {
            ProdottoCarrello justAdded = prodottoCarrelloRepository.save(pcar);
        }catch(Exception e) {
            System.out.println("Eccezione: "+ e.getMessage());
        }

        entityManager.refresh(carrello);
        return carrello;
    }

    /**
     * Cancella il carrello con id passato da parametro.
     * Il metodo è utile qualora un carrello, diventasse ordine, pertanto, per lo stesso utente
     * il carrello in questione verrà distrutto e si creerà una nuova entità Carrello costituita
     * da una lista di prodotti vuota.
     * E' stato necessario, per poter eliminare il carrello in questione,
     * rimuovere i riferimenti alle chiavi esterne tra
     * il carrello e i relativi prodotti presenti in esso.
     */
    @Transactional(readOnly = false)
    public Carrello cancellaCarrello(Long idCarrello) {

        if(carrelloRepository.existsByCarrelloId(idCarrello)) {
            Carrello ret = carrelloRepository.findCarrelloByCarrelloId(idCarrello);
            prodottoCarrelloRepository.deleteByCarrello(ret); //rimuovo i riferimenti ai vincoli delle chiavi dei prodotti
            ret.setProdottiCarrello(new ArrayList<>());
            Utente u = ret.getUtente();
            Carrello nuovoCarrello = new Carrello();        //creo un novo carrello con una lista di prodotti nel carrello vuota
            nuovoCarrello.setProdottiCarrello(new ArrayList<>());
            nuovoCarrello.setUtente(u);
            carrelloRepository.save(nuovoCarrello);
            u.setCarrello(nuovoCarrello);
            try {
                carrelloRepository.delete(ret); //elimino il vecchio carrello
            }catch(Exception e){
                System.out.println("Eccezione carrello eliminato>> " + e.getMessage() );
                }
            return ret;
        }
        return null;
    }

    /**
     * Metodo che permette di eliminare singoli prodotti nel carrello.
     * Se l'utente dovesse inserire un prodotto per errore nel carrello,
     * il metodo sottostante ne consente la rimozione.
     */
    //Cancello dal carrello  tutti i prodotti aventi l'id passato
    public Carrello cancellaProdottoDalCarrello(Long idCarrello, Long prodId){
        Carrello ret = carrelloRepository.findCarrelloByCarrelloId(idCarrello);

        List<ProdottoCarrello> listaProdottiRet = ret.getProdottiCarrello();

        Iterator<ProdottoCarrello> iterator = listaProdottiRet.listIterator();

        while(iterator.hasNext()){
            ProdottoCarrello prod = iterator.next();
            if( prod.getProdotto().getProdottoId() == prodId) {
                iterator.remove();
                prodottoCarrelloRepository.delete(prod);
            }
        }


        return ret;
    }

}

