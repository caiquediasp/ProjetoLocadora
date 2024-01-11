package com.locadora.ProjetoLocadora.dao;

import com.locadora.ProjetoLocadora.util.Contrato;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ContratoDAO {
    ResponseEntity<Contrato> adicionarContrato(Contrato contrato);

    ResponseEntity<List<Contrato>> listarContratos();

    ResponseEntity<Contrato> buscarPorId(String id);

    ResponseEntity<Contrato> renovarContrato(String id, Contrato contrato);

    ResponseEntity<Contrato> excluirContrato(String id);

}
