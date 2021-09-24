package com.ecommerce.storegym.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@EqualsAndHashCode

@Entity
@Table(name= "prodottoInOrdine")
public class ProdottoInOrdine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "idProdottoInOrdine", nullable = false)
    private Long idProdottoInOrdine;


    @ManyToOne
    @JoinColumn(name = "OrdineCorrelato")
    @JsonIgnore
    @ToString.Exclude
    private Ordine ordine;

    @Basic
    @Column(name = "quantitaRichiesta", nullable = false)
    private int quantitaRichiesta;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "prodotto")
    private Prodotto prodotto;
}
