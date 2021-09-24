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
     Se nello stesso controller, due operazioni differenti
     esempio Get e post, senza aver specificato come parametro
     l'URL distinto, spring sarà in grado di disambiguare le due
     differenti chiamate.
     */

    /**
     * l'annotazione @RequestBody indica che l'oggetto prodotto spring deve andarlo a recuperare nel body della richiesta
     * Questo oggetto viene codificato tramite oggetto Json
     */

    /**
     *Carica i prodotti nel db con una richesta json
     */
    @CrossOrigin
    @PostMapping
    public ResponseEntity creaProdotto(@RequestBody Prodotto prodotto) {
       try {
            prodottoService.aggiungiProdotto(prodotto);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(
                    new MessaggioRisposta("Aggiunta prodotto fallita!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new MessaggioRisposta("Aggiunta avvenuta con successo!"), HttpStatus.OK);
    }// creaProdotto

    /**
     * l'annotazione @DeleteMapping specifica quale prodotto eliminare prendendo l'id dell'URL
     */

    /**
     * Metodo inserito a scopo didattico,
     * permette di effettuare una cancellazione di prodotti dal db
     */
    @CrossOrigin
    @DeleteMapping("/cancella/{id}")
    public ResponseEntity cancellaProdotto(@PathVariable(value = "id") Long id) {
        Prodotto op = prodottoService.cancellaProdotto(id);
        if (op != null) {
            return new ResponseEntity<>(new MessaggioRisposta("Prodotto con id:" + id + " cancellato correttamente!"), HttpStatus.OK);
        } else
            return new ResponseEntity<>(new MessaggioRisposta("Nessun prodotto cancellato!"), HttpStatus.OK);
    }//cancellaProdotto


    /** L'oggetto ResponseEntity ha due utilità: se noi ritorniamo come risultato una lista di acquisti, se volessimo
    ritornare il messaggio come oltre all'oggetto, potremmo farlo e questo ci permette di mandare al client la richiesta
    di status */

    /**
     * Mostra prodotti paginati
     */
    @CrossOrigin
    @GetMapping()
    public ResponseEntity mostraTuttiProdottiPaginati(@RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
                                                      @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                                      @RequestParam(value = "sortBy", defaultValue = "prodottoId") String sortBy) {
        List<Prodotto> risultato = prodottoService.mostraTuttiProdottiPaginati(pageNumber, pageSize, sortBy);
        if (risultato.size() <= 0) {
            return new ResponseEntity<>(new MessaggioRisposta("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(risultato, HttpStatus.OK);
    }//mostraTuttiProdottiPaginati

    /**
     * Mostra tutti i prodotti
     */
    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity mostraTuttiProdotti() {
        List<Prodotto> risultato = prodottoService.mostraTuttiProdotti();
        if (risultato.size() <= 0) {
            return new ResponseEntity<>(new MessaggioRisposta("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(risultato, HttpStatus.OK);
    }//mostraTuttiProdotti

    /**
     *  @RequestParam per estrarre parametri di query, parametri di modulo e persino file dalla richiesta.
     *
     *  Mostra prodotti per categoria
     */
    @CrossOrigin
    @GetMapping("/categoria")
    public ResponseEntity mostraPerCategoria(@RequestParam(value = "categoriaProdotto",defaultValue = "ATTREZZI" )String categoriaProdotto) {
        String cp = categoriaProdotto.toUpperCase().trim();
        List<Prodotto>categoria;
        for(Prodotto.CategoriaProdotto pcp: Prodotto.CategoriaProdotto.values())
            if(pcp.name().matches(cp)){
                categoria = prodottoService.mostraProdottiCategoria(pcp);
                return new ResponseEntity<>(categoria, HttpStatus.OK);
            }
            return new ResponseEntity<>(new MessaggioRisposta("Nessun risultato!"), HttpStatus.OK);
    }

    /**
     * Mostra tutti i prodotti per nome
     * Funzione implementata per consentire la ricerca dei prodotti
     * Dalla barra di ricerca lato front end
     */
    @CrossOrigin
    @GetMapping("/nomeProdotti/{nomeProdotto}")
    public ResponseEntity mostraProdottiPerNome(@PathVariable(value = "nomeProdotto") String nomeProdotto ){
        List<Prodotto> risultato = prodottoService.prodottiNome(nomeProdotto);
        if (risultato.size() <= 0) {
            return new ResponseEntity<>(new MessaggioRisposta("Nessun risultato!"), HttpStatus.OK);
        }
        return new ResponseEntity<>(risultato, HttpStatus.OK);


    }
}//ProdottiController

