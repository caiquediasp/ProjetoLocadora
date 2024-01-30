package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.exceptions.ContratanteNaoEncontradoException;
import com.locadora.ProjetoLocadora.exceptions.CpfInvalidoException;
import com.locadora.ProjetoLocadora.repository.ContratanteRepository;
import com.locadora.ProjetoLocadora.util.Contratante;
import com.locadora.ProjetoLocadora.validations.CpfValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public ResponseEntity<Object> buscarContratantePorCpf(String cpf) {
         try {
             cpfValidation.validadorCpf(cpf);
         }
         catch (CpfInvalidoException e) {
             return ResponseEntity
                     .status(HttpStatus.CONFLICT)
                     .body(e.getMessage());
         }

        Contratante contratante = contratanteRepository.findById(cpf)
                .orElseThrow(ContratanteNaoEncontradoException::new);

        return ResponseEntity.ok(contratante);
    }

    public ResponseEntity<Object> quantidadeContratoDoContratante(String cpf) {
        try{
            cpfValidation.validadorCpf(cpf);
        }
        catch (CpfInvalidoException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }

        contratanteRepository.findById(cpf)
                .orElseThrow(ContratanteNaoEncontradoException::new);

        Integer quantidadeContrato = contratanteRepository.quantidadeContratoDoContratante(cpf);

        return ResponseEntity.ok(quantidadeContrato);
    }

    public ResponseEntity<Object> atualizarTelefone(String cpf, String telefone) {
        try{
            cpfValidation.validadorCpf(cpf);
        }
        catch (CpfInvalidoException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }

         Contratante contratante = contratanteRepository.findById(cpf)
                 .orElseThrow(ContratanteNaoEncontradoException::new);

         contratante.setTelefone(telefone);

         contratanteRepository.save(contratante);

         return ResponseEntity.ok(contratante);
    }
}
