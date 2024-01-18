package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.exceptions.ContratanteNaoEncontrado;
import com.locadora.ProjetoLocadora.repository.ContratanteRepository;
import com.locadora.ProjetoLocadora.repository.ContratoRepository;
import com.locadora.ProjetoLocadora.util.Contratante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratanteService {
    @Autowired
    ContratoRepository contratoRepository;
    @Autowired
    ContratanteRepository contratanteRepository;

    public ResponseEntity<List<Contratante>> listarTodosContratantes() {
        List<Contratante> listaContratante = contratanteRepository.findAll();

        return ResponseEntity.ok(listaContratante);
    }

    public ResponseEntity<Contratante> buscarContratantePorCpf(String cpf) {
        Contratante contratante = contratanteRepository.findById(cpf)
                .orElseThrow(() -> new ContratanteNaoEncontrado("Contratante não encontrado com o CPF: " + cpf));

        return ResponseEntity.ok(contratante);
    }

    public ResponseEntity<Integer> quantidadeContratoDoContratante(String cpf) {
        Integer quantidadeContrato = contratanteRepository.quantidadeContratoDoContratante(cpf);

        return ResponseEntity.ok(quantidadeContrato);
    }

    public ResponseEntity<Contratante> atualizarTelefone(String cpf, String telefone) {
         Contratante contratante = contratanteRepository.findById(cpf)
                 .orElseThrow(() -> new ContratanteNaoEncontrado("Contratante não encontrado com o CPF: " + cpf));

         contratante.setTelefone(telefone);

         contratanteRepository.save(contratante);

         return ResponseEntity.ok(contratante);
    }
}
