package com.ecommerce.storegym.controllers;


import com.ecommerce.storegym.entities.Prodotto;
import com.ecommerce.storegym.services.ProdottoService;
import com.ecommerce.storegym.support.exceptions.MessaggioRisposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prodotti")
public class ProdottiController {

    @Autowired
    private ProdottoService prodottoService;

    /**
      Con RequestBody si acquisice l'oggetto e lo si può utilizzare.
      La notazione @Valid vuol dire che i campi devono essere validi,
      rispettando le annotazione delle entity.
      Valida l'oggetto ricevuto. Permette di evitare le verifiche di ogni
      campo per determinarne la correttezza nelle nostre entity.
      Solleva un'eccezione se non sono rispettati i parametri.
     */

    @PostMapping
    public ResponseEntity creaProdotto(@RequestBody Prodotto prodotto) {
        try {
            prodottoService.aggiungiProdotto( prodotto );
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new MessaggioRisposta("Aggiunta prodotto fallita!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new MessaggioRisposta("Aggiunta avvenuta con successo!"), HttpStatus.OK);
    }// creaProdotto

    @GetMapping("/paginazione")
    public ResponseEntity mostraTutti(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "sortBy", defaultValue = "id") String sortBy){
        List<Prodotto> risultato = prodottoService.mostraTuttiProdotti(pageNumber, pageSize, sortBy);
        if ( risultato.size() <= 0 ) {
            return new ResponseEntity<>(new MessaggioRisposta("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(risultato, HttpStatus.OK);
    }
}
