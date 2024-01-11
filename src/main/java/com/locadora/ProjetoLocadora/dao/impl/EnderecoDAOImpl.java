package com.locadora.ProjetoLocadora.dao.impl;

import com.locadora.ProjetoLocadora.dao.EnderecoDAO;
import com.locadora.ProjetoLocadora.repository.EnderecoRepository;
import com.locadora.ProjetoLocadora.util.Endereco;
import org.springframework.beans.factory.annotation.Autowired;

public class EnderecoDAOImpl implements EnderecoDAO {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Override
    public Endereco verificarEnderecoExistente(Endereco endereco) {
        return enderecoRepository.verificarEnderecoExistente(endereco.getCep(), endereco.getBairro(), endereco.getRua(), endereco.getNumero());
    }
}
