package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.repository.EstoqueRepository;
import com.locadora.ProjetoLocadora.util.pecas.Andaime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;

}
