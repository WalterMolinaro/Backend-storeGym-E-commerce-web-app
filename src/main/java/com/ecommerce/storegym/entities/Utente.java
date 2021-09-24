package com.ecommerce.storegym.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "utente")
public class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idUtente", nullable = false)
    private long idUtente;

    @Column(name = "nomeUtente", nullable = false)
    private String nomeUtente;

    @Column(name = "cognomeUtente", nullable = false)
    private String cognomeUtente;

    @Column(name = "telefonoUtente", nullable = false,length = 1024)
    private String telefonoUtente;

    @Column(name = "indirizzoUtente", nullable = false)
    private String indirizzoUtente;

    @Column(name = "email", nullable = false,length = 1024)
    private String email;

    @Column(name = "password", nullable = false,length = 1024)
    private String password;

    @OneToOne
    @ToString.Exclude
    private Carrello carrello;

    @Column(name = "dataRegistrazione",length = 1024)
    private String dataRegistrazione;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.MERGE)
    @JsonIgnore
    @ToString.Exclude
    private List<Ordine> ordini;


}
