package com.locadora.ProjetoLocadora.repository.impl;

import com.locadora.ProjetoLocadora.repository.EnderecoRepository;
import com.locadora.ProjetoLocadora.util.Endereco;
import org.springframework.beans.factory.annotation.Autowired;

public class EnderecoRepositoryImpl implements EnderecoRepository {
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Endereco verificarEnderecoExistente(Endereco endereco) {
        return enderecoRepository.verificarEnderecoExistente(endereco.getCep(), endereco.getBairro(), endereco.getRua(), endereco.getNumero());
    }
}
