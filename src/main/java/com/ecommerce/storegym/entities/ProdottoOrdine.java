package com.ecommerce.storegym.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "prodottoOrdine")
public class ProdottoOrdine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prodottoOrdineId", nullable = false)
    private long prodottoOrdineId;

    @Column(name = "quantitaOrdine", nullable = false)
    private int quantitaOrdine;

    @Column( name = "prezzoAcquisto", nullable = false)
    private double prezzoAcquisto;

    @Column( name = "subTotaleAcquisto", nullable = false)
    private double subTotaleAcquisto;

    @OneToOne
    @JoinColumn(name = "prodottoId")
    @MapsId
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(name = "ordineId")
    private Ordine ordine;
}
