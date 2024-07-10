package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.repository.ContratoRepository;
import com.locadora.ProjetoLocadora.repository.EstoqueRepository;
import com.locadora.ProjetoLocadora.validations.ContratoStatusValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {
    @Autowired
    private ContratoStatusValidation contratoStatusValidation;
    @Autowired
    private EstoqueRepository estoqueRepository;

    public Integer verificaEstoqueAndaime(int tamanho) {
        contratoStatusValidation.validaStatusContrato();

        return estoqueRepository.verificaEstoqueAndaime(tamanho);
    }

    public Integer verificaEstoqueEscora(int tamanho) {
        contratoStatusValidation.validaStatusContrato();

        return estoqueRepository.verificaEstoqueEscora(tamanho);
    }

    public Integer verificaEstoquePlataforma(int tamanho) {
        contratoStatusValidation.validaStatusContrato();

        return estoqueRepository.verificaEstoquePlataforma(tamanho);
    }

    public Integer verificaEstoqueRoldana() {
        contratoStatusValidation.validaStatusContrato();

        return estoqueRepository.verificaEstoqueRoldana();
    }

}
