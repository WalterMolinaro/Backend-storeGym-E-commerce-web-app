package com.ecommerce.storegym.services;

import com.ecommerce.storegym.entities.Carrello;
import com.ecommerce.storegym.entities.ProdottoCarrello;
import com.ecommerce.storegym.entities.Utente;
import com.ecommerce.storegym.repositories.CarrelloRepository;
import com.ecommerce.storegym.repositories.UtenteRepository;
import com.ecommerce.storegym.support.exceptions.EmailGiaEsistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private CarrelloRepository carrelloRepository;

    @Transactional(readOnly = true)
    public List<Utente> mostraTuttiGliUtenti(){
        return utenteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Utente mostraUtente(String email, String password){

        if(!utenteRepository.existsByEmailAndPassword(email,password)){
            throw new IllegalArgumentException("Utente inesistente");
        }

        return utenteRepository.findByEmailAndPassword(email, password);
    }
    @Transactional(readOnly = false)
    public Utente aggiungiUtente(Utente utente) throws EmailGiaEsistenteException {
        if(utenteRepository.existsByEmail(utente.getEmail())){
            throw new EmailGiaEsistenteException();
        }
        Carrello c = new Carrello(utente,new ArrayList<>());
        c.setProdottiCarrello(new ArrayList<>());

        utente.setCarrello(c);
        creaCarrello(c);

        return utenteRepository.save(utente);

    }


    @Transactional(readOnly = false)
    public Carrello creaCarrello(Carrello c){
        return carrelloRepository.save(c);
    }
}
