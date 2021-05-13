package com.ecommerce.storegym.entities;

import com.sun.istack.NotNull;
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
    @Column(name = "prodottoCarrelloId", nullable = false)
    private long prodottoCarrelloId;

    @Column(name = "quantitaCarrello", nullable = false)
    private int quantitaCarrello;

    @Column(name = "subTotaleCarrello", nullable = false)
    private double subTotaleCarrello;

    /**
     * Con @ManyToOne indichiamo a JPA che ProdottoCarrello è in relazione N:1  con Carrello.
     * Quindi il campo carrello lato DB sarà una FK.
     */
    @ManyToOne
    @JoinColumn(name = "carrelloId")
    private Carrello carrello;

    @OneToOne
    @JoinColumn(name = "prodottoId")
    @MapsId
    private Prodotto prodotto;

}
