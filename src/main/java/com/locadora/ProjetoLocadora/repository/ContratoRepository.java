package com.locadora.ProjetoLocadora.repository;

import com.locadora.ProjetoLocadora.util.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, String> {

}
