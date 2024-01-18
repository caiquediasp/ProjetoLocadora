package com.locadora.ProjetoLocadora.repository;

import com.locadora.ProjetoLocadora.util.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, String> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_contrato SET status = 'VENCIDO' WHERE data_devolucao < current_date;"
            , nativeQuery = true)
    void atualizarStatusContrato();

    @Query(value = "SELECT * FROM tb_contrato c WHERE c.cpf_contratante = :cpf"
            , nativeQuery = true)
    List<Contrato> listarContratosDoContratante(String cpf);

    @Query(value = "SELECT * FROM tb_contrato c WHERE c.id_endereco = :id"
            , nativeQuery = true)
    List<Contrato> listarContratosDoEndereco(String id);

    @Query(value = "SELECT * FROM tb_contrato WHERE data_devolucao > current_date;"
           , nativeQuery = true)
    List<Contrato> listarContratosAtivos();

    @Query(value = "SELECT * FROM tb_contrato c WHERE c.data_devolucao < current_date;"
           , nativeQuery = true)
    List<Contrato> listarContratosVencidos();

}
