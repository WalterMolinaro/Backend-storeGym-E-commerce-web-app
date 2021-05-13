package com.ecommerce.storegym.services;

import com.ecommerce.storegym.entities.Prodotto;
import com.ecommerce.storegym.repositories.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 1) mostro tutti i prodotti che appartengono ad una data categoria
 * 2)
 */

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository prodottoRepository;

    /* readOnly serve solo per sapre se è in sola lettura.*/
    @Transactional(readOnly = true)
    public List<Prodotto> mostraProdottiCategoria(Prodotto.CategoriaProdotto categoria){
        if (! (categoria instanceof Prodotto.CategoriaProdotto) ) throw new
                IllegalArgumentException("La categoria passata in input, non è valida!");
        List<Prodotto> ret = prodottoRepository.findByCategoriaProdotto(categoria);
        return ret;
    }//mostraProdottoCategoria

    @Transactional(readOnly = true)
    public List<Prodotto>mostraProdottiNome(String nome){
        return prodottoRepository.findByNomeProdotto(nome);
    }//mostraProdottoNome

    /**
     * Quando si fa un inserimento è sempre buona prassi
     * ritornare il tipo che abbiamo inserito, perché tale
     * oggetto lo diamo come risultato al client
     * dopo l'inserimento. */
    @Transactional(readOnly = false)
    public Prodotto aggiungiProdotto(Prodotto prodotto){
        return prodottoRepository.save(prodotto);
    }

    @Transactional(readOnly = true)
    public List<Prodotto> mostraTuttiProdotti(int pageNumber, int pageSize, String sortBy){
        Pageable paging = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Prodotto> pagedResult = prodottoRepository.findAll(paging);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }


}
