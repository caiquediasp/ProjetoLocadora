package com.locadora.ProjetoLocadora.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository {
    @Query(value = "SELECT quantidade < :quantidade e FROM tb_estoque_andaime WHERE e.tamanho = :tamanho;"
            , nativeQuery = true)
    public Boolean verificaDisponibilidadeAndaime(int tamanho, int quantidade);

    @Query(value = "SELECT quantidade < :quantidade e FROM tb_estoque_escora WHERE e.tamanho = :tamanho;"
            , nativeQuery = true)
    public Boolean verificaDisponibilidadeEscora(int tamanho, int quantidade);

    @Query(value = "SELECT quantidade < :quantidade e FROM tb_estoque_plataforma WHERE e.tamanho = :tamanho;"
            , nativeQuery = true)
    public Boolean verificaDisponibilidadePlataforma(int tamanho, int quantidade);

    @Query(value = "SELECT quantidade < :quantidade e FROM tb_estoque_roldana",
            nativeQuery = true)
    public Boolean verificaDisponibilidadeRoldana(int quantidade);


    @Query(value = "SELECT quantidade e FROM tb_estoque_andaime WHERE e.tamanho = :tamanho;"
            , nativeQuery = true)
    public Boolean verificaEstoqueAndaime(int tamanho, int quantidade);

    @Query(value = "SELECT quantidade e FROM tb_estoque_escora WHERE e.tamanho = :tamanho;"
            , nativeQuery = true)
    public Boolean verificaEstoqueEscora(int tamanho, int quantidade);

    @Query(value = "SELECT quantidade e FROM tb_estoque_plataforma WHERE e.tamanho = :tamanho;"
            , nativeQuery = true)
    public Boolean verificaEstoquePlataforma(int tamanho, int quantidade);

    @Query(value = "SELECT quantidade e FROM tb_estoque_roldana"
            , nativeQuery = true)
    public Boolean verificaEstoqueRoldana(int quantidade);



}
