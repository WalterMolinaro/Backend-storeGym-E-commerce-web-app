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
@Table(name = "indirizzo")
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "indirizzoId", nullable = false)
    private long indirizzoId;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @Column(name = "nazione", nullable = false)
    private String nazione;

    @Column(name = "stato", nullable = false)
    private String stato;

    @Column( name = "citta", nullable = false)
    private String citta;

    @Column( name = "via", nullable = false)
    private String via;

    @Enumerated(EnumType.STRING)
    private TipoIndirizzo tipoIndirizzo;

    public enum TipoIndirizzo{FATTURAZIONE, SPEDIZIONE}

    @Column(name = "numeroCivico", nullable = false)
    private String numeroCivico;

}
