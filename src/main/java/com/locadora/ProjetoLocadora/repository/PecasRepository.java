package com.locadora.ProjetoLocadora.repository;

import com.locadora.ProjetoLocadora.util.Pecas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PecasRepository extends JpaRepository<Pecas, String> {
}
