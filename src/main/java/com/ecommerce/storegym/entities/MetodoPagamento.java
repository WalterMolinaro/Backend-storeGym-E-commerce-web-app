package com.ecommerce.storegym.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "metodoPagamento")
public class MetodoPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metodoPagamentoId")
    private long metodoPagamentoId;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente cliente;

    @Column(name = "dataPagamento")
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    private Pagamento.TipoPagamento tipoPagamento;




}
