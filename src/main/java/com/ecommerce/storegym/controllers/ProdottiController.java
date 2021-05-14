package com.ecommerce.storegym.controllers;


import com.ecommerce.storegym.entities.Prodotto;
import com.ecommerce.storegym.services.ProdottoService;
import com.ecommerce.storegym.support.exceptions.MessaggioRisposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
     Se nello stesso controller, due operazioni differenti
     esempio Get e post, senza aver specificato come parametro
     l'URL distinto, spring sarà in grado di disambiguare le due
     differenti chiamate.
     */

    /** l'annotazione @RequestBody indica che l'oggetto prodotto spring deve andarlo a recuperare nel body della richiesta
    Questo oggetto viene codificato tramite oggetto Json */
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

    @GetMapping
    public List<Prodotto> mostaTuttiProdotti() {
        return prodottoService.mostraTuttiProdotti();
    }//mostraTuttiProdotti

    /* L'oggetto ResponseEntity ha due utilità: se noi ritorniamo come risultato una lista di acquisti, se volessimo
    ritornare il messaggio come oltre all'oggetto, potremmo farlo e questo ci permette di mandare al client la richiesta
    di status */
    @GetMapping("/paginazione")
    public ResponseEntity mostraTuttiProdottiPaginati(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                      @RequestParam(value = "sortBy", defaultValue = "id") String sortBy){
        List<Prodotto> risultato = prodottoService.mostraTuttiProdottiPaginati(pageNumber, pageSize, sortBy);
        if ( risultato.size() <= 0 ) {
            return new ResponseEntity<>(new MessaggioRisposta("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(risultato, HttpStatus.OK);
    }//mostraTuttiProdottiPaginati

    @DeleteMapping("/cancella/{id}")
    public ResponseEntity cancellaProdotto(@PathVariable(value = "id") Long id){
        Optional op = prodottoService.cancellaProdotto(id);
        if( op != null ){
            return new ResponseEntity<>(new MessaggioRisposta("Prodotto con id:"+ id +" cancellato correttamente!"), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(new MessaggioRisposta("Nessun prodotto cancellato!"), HttpStatus.OK);
    }//cancellaProdotto

}