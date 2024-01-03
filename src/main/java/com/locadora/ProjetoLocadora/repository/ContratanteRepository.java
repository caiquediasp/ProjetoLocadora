package com.locadora.ProjetoLocadora.repository;

import com.locadora.ProjetoLocadora.util.Contratante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratanteRepository extends JpaRepository<Contratante, String> {
}
