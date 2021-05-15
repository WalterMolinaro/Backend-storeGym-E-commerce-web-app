package com.ecommerce.storegym.repositories;

import com.ecommerce.storegym.entities.Prodotto;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto,Long> {

    List<Prodotto> findByCategoriaProdotto(Prodotto.CategoriaProdotto categoria);

    Prodotto findByProdottoId(Long id);

    Page<Prodotto> findAll(@NonNull Pageable pageable);
    Prodotto deleteByProdottoId(Long id);

    @Override
    boolean existsById(Long aLong);
}
