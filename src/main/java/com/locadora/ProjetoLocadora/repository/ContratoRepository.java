package com.locadora.ProjetoLocadora.repository;

import com.locadora.ProjetoLocadora.util.Contrato;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends MongoRepository<Contrato, String> {
}
