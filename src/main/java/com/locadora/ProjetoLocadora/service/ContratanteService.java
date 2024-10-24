package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.exceptions.ContratanteNaoEncontradoException;
import com.locadora.ProjetoLocadora.repository.ContratanteRepository;
import com.locadora.ProjetoLocadora.entities.Contratante;
import com.locadora.ProjetoLocadora.validations.CpfValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratanteService {
    @Autowired
    private ContratanteRepository contratanteRepository;
    @Autowired
    private CpfValidation cpfValidation;

    public List<Contratante> listarTodosContratantes() {
        return contratanteRepository.findAll();
    }

    public Contratante buscarContratantePorCpf(String cpf) {
        cpfValidation.validadorCpf(cpf);

        return contratanteRepository.findById(cpf)
                .orElseThrow(() -> new ContratanteNaoEncontradoException(cpf));
    }

    public Integer quantidadeContratoDoContratante(String cpf) {
        cpfValidation.validadorCpf(cpf);

        contratanteRepository.findById(cpf)
                .orElseThrow(() -> new ContratanteNaoEncontradoException(cpf));

        return contratanteRepository.quantidadeContratoDoContratante(cpf);
    }

    public Contratante atualizarTelefone(String cpf, String telefone) {
        cpfValidation.validadorCpf(cpf);

         Contratante contratante = contratanteRepository.findById(cpf)
                 .orElseThrow(() -> new ContratanteNaoEncontradoException(cpf));

         contratante.setTelefone(telefone);

         contratanteRepository.save(contratante);

         return contratante;
    }
}
