package com.ecommerce.storegym.repositories;

import com.ecommerce.storegym.entities.ProdottoInOrdine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoInOrdineRepository extends JpaRepository<ProdottoInOrdine, Long> {
    boolean existsByIdProdottoInOrdine(Long idProdottoInOrdine);
}
