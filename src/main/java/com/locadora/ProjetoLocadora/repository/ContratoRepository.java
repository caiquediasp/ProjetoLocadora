package com.locadora.ProjetoLocadora.repository;

import com.locadora.ProjetoLocadora.util.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, String> {
    @Query(value = "SELECT * FROM tb_contrato WHERE data_devolucao > current_date;"
           , nativeQuery = true)
    List<Contrato> listarContratosAtivos();

    @Query(value = "SELECT * FROM tb_contrato c WHERE c.data_devolucao < current_date;"
           , nativeQuery = true)
    List<Contrato> listarContratosVencidos();

    @Query(value = "SELECT * FROM tb_contrato c WHERE c.cpf_contratante = :cpf"
            , nativeQuery = true)
    List<Contrato> listarContratosDoContratante(String cpf);

}
