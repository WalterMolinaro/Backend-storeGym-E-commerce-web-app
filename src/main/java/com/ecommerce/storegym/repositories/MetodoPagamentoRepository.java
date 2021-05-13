package com.ecommerce.storegym.repositories;

import com.ecommerce.storegym.entities.MetodoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetodoPagamentoRepository extends JpaRepository<MetodoPagamento,Long> {
}
