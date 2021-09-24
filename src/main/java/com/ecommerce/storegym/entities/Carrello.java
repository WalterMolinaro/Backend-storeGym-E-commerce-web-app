package com.ecommerce.storegym.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "carrello")
public class Carrello {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "carrelloId", nullable = false)
    private Long carrelloId;

    @OneToOne
    @ToString.Exclude
    @JsonIgnore
    private Utente utente;


    @OneToMany(mappedBy = "carrello",cascade = CascadeType.MERGE)
    private List<ProdottoCarrello> prodottiCarrello;

    public Carrello(Utente u, ArrayList<ProdottoCarrello> prodotti ){
        this.utente = u;
        this.prodottiCarrello = prodotti;
    }


    public Carrello() {
        prodottiCarrello = new ArrayList<>();
        utente = new Utente();
    }
}
