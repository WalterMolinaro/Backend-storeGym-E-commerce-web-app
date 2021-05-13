package com.ecommerce.storegym.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUtente", nullable = false)
    private long idUtente;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "dataRegistrazione", nullable = false)
    private LocalDate dataRegistrazione;

    @Enumerated(EnumType.STRING)
    @Column(name = "ruoloUtente", nullable = false)
    private RuoloUtente ruoloUtente;

    public enum RuoloUtente{ UTENTE_ADMIN, UTENTE_CLIENTE}

    /**
     * Con @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
     * sto indicando a JPA che c'è una relazione 1:1 con la classe  che mappa la tabella
     * Admin. Inoltre, con cascade = CascadeType.ALL, indichiamo che è attivo il cascade su tutte
     * le operazioni di INSERT, UPDATE, DELETE (non è obbligatorio avere il cascade lato db).
     * Infine, con orphanRemoval = true indichiamo che se un figlio, Admin, rimane "orfano" del padre,
     * Utente, (ovvero ha foreign key null), deve essere cancellato automaticamente.
     *
     * Con @PrimaryKeyJoinColumn, indichiamo a JPA che la tabella Admin ha come chiave primaria
     * la stessa della tabella User.
     */
    @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true )
    @PrimaryKeyJoinColumn
    private Admin admin;

    @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true )
    @PrimaryKeyJoinColumn
    private Cliente cliente;
}
