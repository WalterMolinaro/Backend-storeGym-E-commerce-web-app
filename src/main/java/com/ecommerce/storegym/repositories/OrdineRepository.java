package com.ecommerce.storegym.repositories;

import com.ecommerce.storegym.entities.Ordine;
import com.ecommerce.storegym.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrdineRepository extends JpaRepository<Ordine,Long> {

    List<Ordine> findByUtente_IdUtente(Long idUtente);
    boolean existsOrdineByUtente(Utente utente);
    Ordine findByUtente_IdUtenteAndDataCreazione(Long idUtente, String dataCreazione);
}
