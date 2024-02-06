package com.locadora.ProjetoLocadora.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository {
    @Query(value = "SELECT quantidade < :quantidade e FROM tb_estoque_andaime WHERE e.tamanho = :tamanho;")
    public Boolean verificaEstoqueAndaime(int tamanho, int quantidade);

}
