package com.ecommerce.storegym.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "ordine")
public class Ordine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ordineId", nullable = false)
    private long ordineId;

    @Column(name = "dataCreazione", nullable = false)
    private LocalDate dataCreazione;

    @Column(name = "dataSpedizione")
    private LocalDate dataSpedizione;

    public enum StatoOrdine{COMPLETATO, ELIMINATO, RIFIUTATO}

    @Enumerated(EnumType.STRING)
    private StatoOrdine statoOrdine;

    @Column(name= "totaleOrdine")
    private double totaleOrdine;

    @OneToMany(mappedBy = "ordine")
    private Set<ProdottoOrdine> prodottoOrdine;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;
}
