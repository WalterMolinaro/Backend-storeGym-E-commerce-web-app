package com.ecommerce.storegym.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "prodotto")
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prodottoId", nullable = false)
    private long prodottoId;

    @Column(name = "nomeProdotto", nullable = false)
    private String nomeProdotto;


    @Column(name = "immagineProdotto",length = 1024)
    private String immagineProdotto;

    @Column(name = "peso")
    private double peso;

    @Column(name = "prezzo", nullable = false)
    private double prezzo;

    @Enumerated(EnumType.STRING)
    private CategoriaProdotto categoriaProdotto;

    @Column(name= "descrizioneProdotto", length = 1024)
    private String descrizioneProdotto;



    public enum CategoriaProdotto{INTEGRATORI, PESI, ATTREZZI}

    @Column(name = "quantitaTotale")
    private int quantitaTotale;



    /**
    @VERSION: consente di implemetare i lock ottimistici:
    Nel dB aggiungo una colonna che si chiama verison (long).
     */
    @Version
    @Column(name = "version", nullable = false)
    @JsonIgnore
    private long version;


    //il mappedby indica l'entità che possiede la relzazione, nel mio case il prodottoCarrello possiede il prodotto
    @OneToMany(targetEntity = ProdottoCarrello.class,
            mappedBy = "prodotto",cascade = CascadeType.MERGE)
    @ToString.Exclude
    @JsonIgnore
    private List<ProdottoCarrello> prodottiCarrelloLista;

    @OneToMany(targetEntity = ProdottoInOrdine.class,mappedBy = "prodotto", cascade = CascadeType.MERGE)
    @ToString.Exclude
    @JsonIgnore
    private List<ProdottoInOrdine> prodottiInOrdine;



}
