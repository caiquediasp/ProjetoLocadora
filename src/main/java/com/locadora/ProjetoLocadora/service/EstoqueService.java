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

    public ResponseEntity<Integer> verificaEstoqueAndaime(int tamanho) {
        contratoStatusValidation.validaStatusContrato();
        Integer quantidade = estoqueRepository.verificaEstoqueAndaime(tamanho);

        return ResponseEntity.ok(quantidade);
    }

    public ResponseEntity<Integer> verificaEstoqueEscora(int tamanho) {
        contratoStatusValidation.validaStatusContrato();
        Integer quantidade = estoqueRepository.verificaEstoqueEscora(tamanho);

        return ResponseEntity.ok(quantidade);
    }

    public ResponseEntity<Integer> verificaEstoquePlataforma(int tamanho) {
        contratoStatusValidation.validaStatusContrato();
        Integer quantidade = estoqueRepository.verificaEstoquePlataforma(tamanho);

        return ResponseEntity.ok(quantidade);
    }

    public ResponseEntity<Integer> verificaEstoqueRoldana() {
        contratoStatusValidation.validaStatusContrato();
        Integer quantidade = estoqueRepository.verificaEstoqueRoldana();

        return ResponseEntity.ok(quantidade);
    }

}
