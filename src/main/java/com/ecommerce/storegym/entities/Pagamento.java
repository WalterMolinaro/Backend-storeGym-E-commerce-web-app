package com.ecommerce.storegym.entities;

import com.sun.istack.NotNull;
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
@Table(name = "pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pagamentoId", nullable = false)
    private long pagamentoId;

    @Column(name = "dataPagamento")
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    @NotNull
    private TipoPagamento tipoPagamento;

    public enum TipoPagamento{CARTA_DI_CREDITO,PAYPAL,BONIFICO_BANCARIO}

    @OneToOne
    @MapsId
    private Ordine ordine;

}
