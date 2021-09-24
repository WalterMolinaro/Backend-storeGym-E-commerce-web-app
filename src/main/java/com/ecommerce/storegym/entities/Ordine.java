package com.ecommerce.storegym.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
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
    private String dataCreazione;

    @Column(name= "totaleOrdine")
    private double totaleOrdine;

    @ManyToOne
    @JoinColumn(name = "acquirente")
    private Utente utente;

    @OneToMany(mappedBy = "ordine", cascade = CascadeType.MERGE)
    private List<ProdottoInOrdine> prodottiInOrdine;

}
