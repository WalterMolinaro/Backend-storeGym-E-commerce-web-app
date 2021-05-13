package com.ecommerce.storegym.repositories;

import com.ecommerce.storegym.entities.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto,Long> {

    List<Prodotto> findByCategoriaProdotto(Prodotto.CategoriaProdotto categoria);

    List<Prodotto> findByNomeProdotto(String nome);
}
