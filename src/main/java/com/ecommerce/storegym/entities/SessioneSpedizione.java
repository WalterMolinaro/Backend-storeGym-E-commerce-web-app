package com.ecommerce.storegym.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name ="sessioneSpedizione")
public class SessioneSpedizione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sessioneSpedizioneId")
    private long sessioneSpedizioneId;

    @Column(name = "dataSpedizione")
    private LocalDate dataSpedizione;

    @Enumerated(EnumType.STRING)
    private StatoSpedizione statoSpedizione;

    public enum StatoSpedizione{IN_TRANSITO, CONSEGNATO, IN_DEPOSITO}

    @OneToOne
    private Ordine ordine;
}

