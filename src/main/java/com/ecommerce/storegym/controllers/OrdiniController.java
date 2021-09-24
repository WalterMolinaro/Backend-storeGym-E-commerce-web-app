package com.ecommerce.storegym.controllers;

import com.ecommerce.storegym.entities.Ordine;
import com.ecommerce.storegym.services.OrdineService;
import com.ecommerce.storegym.support.exceptions.QuantitaProdottoNonDisponibile;
import com.ecommerce.storegym.support.exceptions.UtenteNonTrovatoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

@RestController
@RequestMapping("/ordini")
public class OrdiniController {

    @Autowired
    private OrdineService ordineService;

    /**
     * Crea un ordine verificando che la quantità del prodotto richesta sia disponibile nel db
     *  ritorna l'oggetto inserito.
     * */
    @CrossOrigin
    @PostMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ResponseEntity creaOrdine(@RequestBody Ordine ordine) { // è buona prassi ritornare l'oggetto inserito
        try {
            return new ResponseEntity<>(ordineService.aggiungiOrdine(ordine), HttpStatus.OK);
        } catch (QuantitaProdottoNonDisponibile e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantità prodtto non disponibile!", e); // realmente il messaggio dovrebbe essrere più esplicativo (es. specificare il prodotto di cui non vi è disponibilità)
        }
    }
    /**
     * Ottiene tutti gli ordini dell'utente passato da Path variable della richiesta front end
     * */
    @CrossOrigin
    @GetMapping("/{idUtente}")
    public List<Ordine> getOrdini(@PathVariable(value = "idUtente") Long idUtente) {
        try {
            return ordineService.getOrdiniDiUtente(idUtente);
        } catch (UtenteNonTrovatoException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Utente non trovato!", e);
        }
    }
    /**
    Ottiene un ordine specifico per ogni utente, prendendone l'id dell'utente come corpo della richiesta
     e il momento esatto di creazione dell'ordine
     */
    @CrossOrigin
    @GetMapping("/specifico/{idUtente}/{dataCreazione}")
    public Ordine getOrdineSpecifico(@PathVariable(value = "idUtente") Long idUtente,
                            @PathVariable(value = "dataCreazione") String dataCreazione) {
            return ordineService.getOrdineSpecificoUtente(idUtente, dataCreazione);
    }
}
