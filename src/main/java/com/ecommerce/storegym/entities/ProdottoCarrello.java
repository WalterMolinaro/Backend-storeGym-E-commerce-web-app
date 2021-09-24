package com.ecommerce.storegym.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "prodottoCarrello")
public class ProdottoCarrello {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prodottoCarrelloId")
    private Long prodottoCarrelloId;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "prodotto", nullable = false)
    private Prodotto prodotto;

    @ManyToOne
    @JoinColumn(name = "carrello_relativo")
    @JsonIgnore
    @ToString.Exclude
    private Carrello carrello;

   @Column(name = "quantitaRichiesta")
    public int quantitaRichiesta;



}
