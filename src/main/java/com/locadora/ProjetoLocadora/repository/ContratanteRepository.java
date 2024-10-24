package com.locadora.ProjetoLocadora.repository;

import com.locadora.ProjetoLocadora.entities.Contratante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratanteRepository extends JpaRepository<Contratante, String> {
    @Query(value = "SELECT count(*) FROM contrato c WHERE c.cpf_contratante = :cpf"
            , nativeQuery = true)
    Integer quantidadeContratoDoContratante(String cpf);

}
