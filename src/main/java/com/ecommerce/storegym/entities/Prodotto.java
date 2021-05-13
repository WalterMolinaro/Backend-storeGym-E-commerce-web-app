package com.ecommerce.storegym.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "prodotto")
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prodottoId", nullable = false)
    private long prodottoId;

    @Column(name = "nomeProdotto", nullable = false)
    private String nomeProdotto;

    @Column(name = "immagineProdotto")
    private String immagineProdotto;

    @Column(name = "peso")
    private int peso;

    @Column(name = "prezzo", nullable = false)
    private double prezzo;

    /**
     * Creo la distinzione tra prodottoCarrello e prodottoOrdine, poiché sebbene possano
     * sembrare quasi le stesse entità, il prodotto nel carrello può ancora essere modificato,
     * viceversa, un ordine non può più essere modificato.
     *
     * Così facendo riesco a rendere persistente sia il carrello, sia riesco a tenermi traccia degli
     * ordini che ho effettuato.
     */
    @OneToOne(mappedBy = "prodotto", cascade = CascadeType.ALL, orphanRemoval = true )
    @PrimaryKeyJoinColumn
    private ProdottoCarrello prodottoCarrello;

    @OneToOne(mappedBy = "prodotto", cascade = CascadeType.ALL,orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private ProdottoOrdine prodottoOrdine;

    @Enumerated(EnumType.STRING)
    private CategoriaProdotto categoriaProdotto;

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

}
