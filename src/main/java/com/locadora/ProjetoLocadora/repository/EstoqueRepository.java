package com.locadora.ProjetoLocadora.repository;

import com.locadora.ProjetoLocadora.entities.Pecas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Pecas, Integer> {
    @Query(value = "SELECT quantidade FROM estoqueandaime WHERE tamanho = :tamanho"
            , nativeQuery = true)
    public Integer verificaEstoqueAndaime(int tamanho);

    @Query(value = "SELECT quantidade FROM estoqueescora WHERE tamanho = :tamanho"
            , nativeQuery = true)
    public Integer verificaEstoqueEscora(int tamanho);

    @Query(value = "SELECT quantidade FROM estoqueplataforma WHERE tamanho = :tamanho"
            , nativeQuery = true)
    public Integer verificaEstoquePlataforma(int tamanho);

    @Query(value = "SELECT quantidade FROM estoqueroldana"
            , nativeQuery = true)
    public Integer verificaEstoqueRoldana();



}
