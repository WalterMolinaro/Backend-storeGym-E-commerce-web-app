package com.ecommerce.storegym.controllers;

import com.ecommerce.storegym.entities.Utente;

import com.ecommerce.storegym.services.UtenteService;
import com.ecommerce.storegym.support.exceptions.EmailGiaEsistenteException;
import com.ecommerce.storegym.support.exceptions.MessaggioRisposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/utente")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    /**
     * Cross-origin resource sharing (CORS) è un protocollo
     * standard che definisce l'interazione tra un browser e un server
     * per gestire in modo sicuro le richieste HTTP cross-origin.
     *
     * In poche parole, una richiesta HTTP cross-origin è
     * una richiesta a una risorsa specifica, che si trova in un'origine diversa,
     * ovvero un dominio, un protocollo e una porta, rispetto a quella del client che esegue la richiesta.
     */


    /**
     * Inserisce un utente nel db. Viene passato come corpo della richiesta
     */
    @CrossOrigin
    @PostMapping
    public  ResponseEntity aggiungiUtente(@RequestBody Utente utente){
        Utente u;
        try {
            u = utenteService.aggiungiUtente(utente);
        } catch(EmailGiaEsistenteException emailGiaEsistenteException){
            return new ResponseEntity<>(new MessaggioRisposta("Email già esistente"),HttpStatus.CONFLICT);

        } catch(Exception e){

            return new ResponseEntity<>(new MessaggioRisposta("Aggiunta utente fallita!"), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new MessaggioRisposta("Aggiunta utente avvenuta con successo!"), HttpStatus.OK);
    }

    /**
     * Restituisce un utente dal database, passando come parametri email e password.
     * Si verifica nel service dell'utente che è presente il controllo sull'email e la password valide
     */
    @CrossOrigin
    @GetMapping("/login")
    public ResponseEntity mostraUtente(@RequestParam(value = "email")String email,
                                       @RequestParam(value = "password")String password){
        Utente u;
        try {

            u = utenteService.mostraUtente(email, password);
        }catch (Exception e){

            return new ResponseEntity<>( new MessaggioRisposta("Utente inesistente"),HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(u, HttpStatus.OK);

    }
}
