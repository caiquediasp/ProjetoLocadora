package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.exceptions.ContratanteNaoEncontradoException;
import com.locadora.ProjetoLocadora.repository.ContratanteRepository;
import com.locadora.ProjetoLocadora.util.Contratante;
import com.locadora.ProjetoLocadora.validations.CpfValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratanteService {
    @Autowired
    ContratanteRepository contratanteRepository;
    @Autowired
    CpfValidation cpfValidation;

    public ResponseEntity<List<Contratante>> listarTodosContratantes() {
        List<Contratante> listaContratante = contratanteRepository.findAll();

        return ResponseEntity.ok(listaContratante);
    }

    public ResponseEntity<Contratante> buscarContratantePorCpf(String cpf) {
        cpfValidation.validadorCpf(cpf);

        Contratante contratante = contratanteRepository.findById(cpf)
                .orElseThrow(() -> new ContratanteNaoEncontradoException(cpf));

        return ResponseEntity.ok(contratante);
    }

    public ResponseEntity<Integer> quantidadeContratoDoContratante(String cpf) {
        cpfValidation.validadorCpf(cpf);

        contratanteRepository.findById(cpf)
                .orElseThrow(() -> new ContratanteNaoEncontradoException(cpf));

        Integer quantidadeContrato = contratanteRepository.quantidadeContratoDoContratante(cpf);

        return ResponseEntity.ok(quantidadeContrato);
    }

    public ResponseEntity<Contratante> atualizarTelefone(String cpf, String telefone) {
        cpfValidation.validadorCpf(cpf);

         Contratante contratante = contratanteRepository.findById(cpf)
                 .orElseThrow(() -> new ContratanteNaoEncontradoException(cpf));

         contratante.setTelefone(telefone);

         contratanteRepository.save(contratante);

         return ResponseEntity.ok(contratante);
    }
}
