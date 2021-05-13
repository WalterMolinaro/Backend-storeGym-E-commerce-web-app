package com.ecommerce.storegym.repositories;

import com.ecommerce.storegym.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * L'interfaccia JpaReposiotory estende PagingAndSortingRepository che a sua volta estende CrudRepository.
 * JpaRepository ha quindi più metodi.
 * Per quando riguardando i tipi nelle <Utente,Long> si passa l'oggetto della repository specifica per ogni entitity
 * e la successiva sua chiave. E' molto meglio usare sempre interi/Long come chiave principale.
 * Al massimo si possono inserire vincoli di Unique come chiavi secondarie.
 * Se come chiave primaria ci fosse stato un qualcosa di differente,si sarebbe dovuto implementare una classe
 * in più che avrebbe dovuto contenere 2 parametri, ( nome e cognome). Al posto
 * di Long ci sarebbe dovuto essere inserta quella nuova classe. Ne risentirebbe anche la  manutenibilità del codice.
 */
@Repository
public interface UtenteRepository extends JpaRepository<Utente,Long> {

}
