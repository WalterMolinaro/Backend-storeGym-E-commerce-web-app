package com.ecommerce.storegym.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString

@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "adminId",nullable = false)
    private long adminId;

    @Column(name = "nomeAdmin" , nullable = false)
    private String nomeAdmin;

    /**
     * Con @OneToOne e @JoinColumn(name = "user_id") stiamo dicendo a JPA che la colonna FK della relazione 1:1
     * è mappata lato tabella Admin.
     *
     * Con @MapsId stiamo inoltre dicendo che la PK è anche FK della tabella Admin.
     */
    @OneToOne
    @JoinColumn(name = "adminId")
    @MapsId
    private Utente utente;
}
