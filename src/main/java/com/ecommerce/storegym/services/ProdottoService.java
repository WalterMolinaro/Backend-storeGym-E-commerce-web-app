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
import java.util.Optional;

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
        List<Prodotto> ret = prodottoRepository.findByCategoriaProdotto(categoria);
        return ret;
    }//mostraProdottoCategoria


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
    public List<Prodotto> mostraTuttiProdottiPaginati(int pageNumber, int pageSize, String sortBy){
        Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Prodotto> pagedResult =prodottoRepository.findAll(page);
        if ( pagedResult.hasContent() ) {
            return pagedResult.getContent();
        }
        else {
            return new ArrayList<>();
        }
    }
    @Transactional(readOnly = false)
    public Prodotto cancellaProdotto(Long id){
        if(prodottoRepository.existsById(id)) {
            Prodotto ret = prodottoRepository.findByProdottoId(id);
            prodottoRepository.deleteByProdottoId(id);
            return ret;
        }
        return null;
    }

}
