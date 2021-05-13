package com.ecommerce.storegym.repositories;

import com.ecommerce.storegym.entities.Cliente;
import com.ecommerce.storegym.entities.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine,Long> {
/*
    List<Cliente>findByDataCreazioneMonth(LocalDate mese, Pageable pagina);

    Set<Ordine> findByStatoOrdine(Ordine.StatoOrdine statoOrdine);
*/

}
