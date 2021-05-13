package com.ecommerce.storegym.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "carrello")
public class Carrello {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "carrelloId")
    private long carrelloId;

    @OneToOne(mappedBy = "carrello",  cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name= "clienteId")
    private Cliente cliente;

    /**
     * Con @OneToMany(mappedBy = "carrello") stiamo dicendo a JPA che questa tabella è in relazione 1:N
     * con la classe ProdottoCarrello che mappa la tabella Carrello.
     * Inoltre, con mappedBy indichiamo a JPA che ProdottoCarrello ha un
     * attributo chiamato carrello per mappare la relazione inversa N:1.
     */
    @OneToMany(mappedBy = "carrello")
    private Set<ProdottoCarrello> prodottiCarrello;

    @Column(name = "importoTotale")
    private double importoTotale;


}
