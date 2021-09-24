package com.ecommerce.storegym.controllers;

import com.ecommerce.storegym.entities.Carrello;
import com.ecommerce.storegym.entities.Prodotto;

import com.ecommerce.storegym.entities.ProdottoCarrello;
import com.ecommerce.storegym.repositories.CarrelloRepository;
import com.ecommerce.storegym.services.CarrelloService;
import com.ecommerce.storegym.support.exceptions.MessaggioRisposta;
import com.ecommerce.storegym.support.exceptions.QuantitaProdottoNonDisponibile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrello")
public class CarrelloController {

    @Autowired
    private CarrelloService carrelloService;

    @Autowired
    private CarrelloRepository carrelloRepository;

    /**
     * Aggiunge Prodotto nel carrello, la quantià richiesta
     * di tale prodotto è passata come path variable lato front end
     * il prodotto viene inserito nel carrello dell'utente,
     * e il carrello verrà aggiornato
     */
    @CrossOrigin
    @PostMapping("/aggiungiProdotto/{idCarrello}/{quantitaRichiesta}")
    public ResponseEntity aggiungiProdottoInCarrello(@RequestBody Prodotto prodotto,
                                                     @PathVariable(value = "quantitaRichiesta") int quantitaRichiesta,
                                                     @PathVariable(value = "idCarrello") Long idCarrello) {
        try {
            carrelloService.aggiungiProdotto(idCarrello, prodotto, quantitaRichiesta);
        } catch (RuntimeException | QuantitaProdottoNonDisponibile e) {

            return new ResponseEntity<>(
                    new MessaggioRisposta("Aggiunta prodotto nel carrello fallita!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new MessaggioRisposta("Aggiunta avvenuta con successo!"), HttpStatus.OK);
    }// creaProdotto


    /**
     * Mostra tutti i prodotti carrello, resi persistente per ogni utente,
     * Dalla presenza della sola password
     */
    @CrossOrigin
    @GetMapping("/tuttiProdotti/{emailUtente}")
    // naturalmente è necessario effettuare il log in dell'utente, questa cosa la si verifica lato front end
    public ResponseEntity mostraTuttiProdotti(@PathVariable(value = "emailUtente") String emailUtente) {
        List<ProdottoCarrello> risultato = carrelloService.mostraTuttiProdottiInCarrello(emailUtente);

        if (risultato.size() <= 0) {
            return new ResponseEntity<>(new MessaggioRisposta("Nessun risultato!"), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(risultato, HttpStatus.OK);
    }//mostraTuttiProdotti

    /**
     * Cancella un carrello, questo metodono è
     * fondamentale quando tutti i prodotti del carrello
     * diventano ordine.
     *
     */
    @CrossOrigin
    @DeleteMapping("/cancella/{idCarrello}")
    public ResponseEntity cancellaProdottiCarrello(@PathVariable(value = "idCarrello") Long idCarrello) {

        Carrello carrello = carrelloService.cancellaCarrello(idCarrello);
        if (carrello != null) {
            return new ResponseEntity<>(new MessaggioRisposta("Carrello con id: " + idCarrello + "eliminato!"), HttpStatus.OK);
        } else
            return new ResponseEntity<>(new MessaggioRisposta("Nessuna cancellazione di carrelli effettuata"), HttpStatus.BAD_REQUEST);
    }

    /**
     * Cancella singoli prodotti dal carrello
     * aventi id specificato, dal pathvariable della richiesta
     */
    @CrossOrigin
    @DeleteMapping("/cancellaProdotto/{idCarrello}/{prodId}")
    public ResponseEntity cancellaSingoliProdotti(@PathVariable(value = "idCarrello") Long idCarrello, @PathVariable(value = "prodId") Long prodId) {
        Carrello car = carrelloService.cancellaProdottoDalCarrello(idCarrello, prodId);
        List<ProdottoCarrello> prodottiCar = car.getProdottiCarrello();
        for (ProdottoCarrello prod : prodottiCar)
            if (prod.getProdotto().getProdottoId() == prodId)
                return new ResponseEntity<>(new MessaggioRisposta("Nessuna cancellazione di prodotti effettuata"), HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(new MessaggioRisposta("Prodotto/i eliminato/i"), HttpStatus.OK);
    }
}
