package com.locadora.ProjetoLocadora.validations;

import com.locadora.ProjetoLocadora.exceptions.QuantidadeInvalidaException;
import com.locadora.ProjetoLocadora.repository.EstoqueRepository;
import com.locadora.ProjetoLocadora.util.Pecas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstoqueValidation {
    @Autowired
    private EstoqueRepository estoqueRepository;

    public void verificaDisponibilidadePecas(Pecas pecas) {
        if(pecas.getAndaime().getQtdAndaime() > 0)
            if(!estoqueRepository.verificaDisponibilidadeAndaime(
                    pecas.getAndaime().getTamanhoAndaime(), pecas.getAndaime().getQtdAndaime()))
                throw new QuantidadeInvalidaException("andaimes");

        if(pecas.getEscora().getQtdEscora() > 0 )
            if (!estoqueRepository.verificaDisponibilidadeEscora(
                    pecas.getEscora().getTamanhoEscora(), pecas.getEscora().getTamanhoEscora()))
                throw new QuantidadeInvalidaException("escoras");

        if (pecas.getPlataforma().getQtdPlataforma() > 0)
            if(!estoqueRepository.verificaDisponibilidadePlataforma(
                    pecas.getPlataforma().getTamanhoPlataforma(), pecas.getPlataforma().getQtdPlataforma()))
                throw new QuantidadeInvalidaException("plataformas");

        if(pecas.getRoldana().getQtdRoldana() > 0)
            if(estoqueRepository.verificaDisponibilidadeRoldana(
                    pecas.getRoldana().getQtdRoldana()))
                throw new QuantidadeInvalidaException("roldanas");

    }


}
