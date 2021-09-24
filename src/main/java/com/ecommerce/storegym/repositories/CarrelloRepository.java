package com.ecommerce.storegym.repositories;

import com.ecommerce.storegym.entities.Carrello;
import com.ecommerce.storegym.entities.Prodotto;

import com.ecommerce.storegym.entities.ProdottoCarrello;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello,Long> {

    //naturalmente ogni utente ha una sua lista di prodotti
    Carrello findCarrelloByCarrelloId(Long carrelloId);

    boolean existsByCarrelloId(Long carrelloId);
    Carrello deleteByCarrelloId(Long idCarrello);
}
