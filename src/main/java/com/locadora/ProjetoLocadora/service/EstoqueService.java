package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.repository.ContratoRepository;
import com.locadora.ProjetoLocadora.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {
    @Autowired
    private ContratoRepository contratoRepository;
    @Autowired
    private EstoqueRepository estoqueRepository;

    public ResponseEntity<Integer> verificaEstoqueAndaime(int tamanho) {
        contratoRepository.atualizarStatusContrato();
        Integer quantidade = estoqueRepository.verificaEstoqueAndaime(tamanho);

        return ResponseEntity.ok(quantidade);
    }

    public ResponseEntity<Integer> verificaEstoqueEscora(int tamanho) {
        contratoRepository.atualizarStatusContrato();
        Integer quantidade = estoqueRepository.verificaEstoqueEscora(tamanho);

        return ResponseEntity.ok(quantidade);
    }

    public ResponseEntity<Integer> verificaEstoquePlataforma(int tamanho) {
        contratoRepository.atualizarStatusContrato();
        Integer quantidade = estoqueRepository.verificaEstoquePlataforma(tamanho);

        return ResponseEntity.ok(quantidade);
    }

    public ResponseEntity<Integer> verificaEstoqueRoldana() {
        contratoRepository.atualizarStatusContrato();
        Integer quantidade = estoqueRepository.verificaEstoqueRoldana();

        return ResponseEntity.ok(quantidade);
    }

}
