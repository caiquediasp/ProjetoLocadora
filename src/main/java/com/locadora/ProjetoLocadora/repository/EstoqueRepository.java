package com.locadora.ProjetoLocadora.repository;

import com.locadora.ProjetoLocadora.util.Pecas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Pecas, Integer> {
    @Query(value = "SELECT quantidade FROM tb_estoque_andaime WHERE tamanho = :tamanho"
            , nativeQuery = true)
    public Integer verificaEstoqueAndaime(int tamanho);

    @Query(value = "SELECT quantidade FROM tb_estoque_escora WHERE tamanho = :tamanho"
            , nativeQuery = true)
    public Integer verificaEstoqueEscora(int tamanho);

    @Query(value = "SELECT quantidade FROM tb_estoque_plataforma WHERE tamanho = :tamanho"
            , nativeQuery = true)
    public Integer verificaEstoquePlataforma(int tamanho);

    @Query(value = "SELECT quantidade FROM tb_estoque_roldana"
            , nativeQuery = true)
    public Integer verificaEstoqueRoldana();



}
