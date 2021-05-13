package com.ecommerce.storegym.repositories;

import com.ecommerce.storegym.entities.ProdottoOrdine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoOrdineRepository extends JpaRepository<ProdottoOrdine,Long> {
}
