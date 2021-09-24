package com.ecommerce.storegym.repositories;

import com.ecommerce.storegym.entities.Carrello;
import com.ecommerce.storegym.entities.ProdottoCarrello;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdottoCarrelloRepository  extends JpaRepository<ProdottoCarrello, Long> {
    List<ProdottoCarrello> deleteByCarrello(Carrello carrello);
    List<ProdottoCarrello> deleteByCarrelloAndProdotto_ProdottoId(Carrello carrello, Long prodId);
}
