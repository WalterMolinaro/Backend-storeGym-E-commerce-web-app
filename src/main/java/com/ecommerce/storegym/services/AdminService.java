
package com.ecommerce.storegym.services;

import com.ecommerce.storegym.entities.Cliente;
import com.ecommerce.storegym.entities.Ordine;
import com.ecommerce.storegym.repositories.ClienteRepository;
import com.ecommerce.storegym.repositories.OrdineRepository;
import com.ecommerce.storegym.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;
import java.util.Set;


@Service
public class AdminService {

    /** AUTOWIRED: Spring quando crea questa istanza,la inietterà
     *      Esegue questa cosa automaticamente, la cosa positiva è che spring crea un'istanza diClienteRepository,
     *      la prima volta che è stata effettuata la richiesta, e quando non serve più,
     *      la inietterà in un pool dove vengono accumulate
     *      varie istanze, anche uguali. Questo accande perché quando c'è
     *      bisogno di usare quel tipo di repository essa viene utilizzata prendendo l'istanza iniettata
     *      precedentemente dal pool e collegandola ad un service.
     *      Il pro è che non vengono ogni volta rieffettuate le istanziazioni dei repository utilizzando
     *      la vecchie istanze usate precedentemente. Il pool ovviamente è gestito da Spring. */
/*

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OrdineRepository ordineRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    /**
     * 1) Voglio che l'admin possa visualizzare la lista dei clienti
     * che hanno acquistato prodotti in un dato mese dell'anno dell'anno
     *
     * 2) Voglio che l'admin posso verficiare chi è il cliente che ha speso di più
     *
     * 3) l'admin può visualizzare la lista degli ordini che ha fatto un cliente
     *
     * 4) l'admin può visualizzare qual è la categoria di prodotti che viene acquistata di più
     *
     * 5) Voglio che l'admin possa visualizzare tutti la lista dei clienti che si sono registrati
     */

    /*
    //1
    @Transactional(readOnly = true)
    public List<Cliente> mostraClientiAcquisti(int numeroPagina, int dimensionePagina, LocalDate MeseDataAcquisto){
        Pageable pagina = (Pageable) PageRequest.of(numeroPagina, dimensionePagina, Sort.unsorted() );
        Page<Cliente> paginaRisultato = (Page<Cliente>) ordineRepository.findByDataCreazioneMonth(MeseDataAcquisto, pagina );
        if( paginaRisultato.hasContent() ){
            return paginaRisultato.getContent();
        }
        else return new ArrayList<>();
    }



    //2
    @Transactional(readOnly = true)
    public Cliente maggioreSpesa(){
        Cliente ret = null;
        double maxPagamento = 0.0;
        Set<Ordine>ordiniPagati = ordineRepository.findByStatoOrdine(Ordine.StatoOrdine.COMPLETATO);
        for(Ordine ordine : ordiniPagati ){
            double maxParziale = ordine.getTotaleOrdine();
            if( maxParziale >= maxPagamento) {
                maxPagamento = maxParziale;
                ret = ordine.getCliente();
            }
        }
        return ret;
    }

*/

}
