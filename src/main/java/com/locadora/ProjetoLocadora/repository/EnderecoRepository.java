package com.locadora.ProjetoLocadora.repository;

import com.locadora.ProjetoLocadora.util.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, String> {
}
