package com.ecommerce.storegym.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode

@Entity
@Table(name = "cliente")
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente", nullable = false)
    private long idCliente;


    @Column(name = "nomeCliente", nullable = false)
    private String nomeCliente;


    @Column(name = "cognomeCliente", nullable = false)
    private String cognomeCliente;

    @Column(name = "telefonoCliente", nullable = false)
    private String telefonoCliente;


    @Column(name = "indirizzoCliente", nullable = false)
    private String indirizzoCliente;

    @Column(name = "compagniaCliente")
    private String compagniaCliente;

    @OneToOne
    @JoinColumn(name = "clienteId")
    @MapsId
    private Utente utente;

    @OneToOne
    private Carrello carrello;

    @OneToMany(mappedBy = "cliente")
    private Set<Ordine> ordini;

    @OneToMany(mappedBy = "cliente")
    private Set<Indirizzo> indirizzi;

    @OneToMany(mappedBy = "cliente")
    private Set<MetodoPagamento> metodiPagamento;
}
