package com.ecommerce.storegym.repositories;

import com.ecommerce.storegym.entities.ProdottoCarrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoCarrelloRepository extends JpaRepository<ProdottoCarrello,Long> {
}
