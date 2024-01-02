package com.locadora.ProjetoLocadora.dao;

import com.locadora.ProjetoLocadora.exceptions.ContratoNaoEncontrado;
import com.locadora.ProjetoLocadora.repository.ContratoRepository;
import com.locadora.ProjetoLocadora.util.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContratoDAOImpl implements ContratoDAO{
    @Autowired
    private ContratoRepository contratoRepository;

    public ResponseEntity<Contrato> adicionarContrato(Contrato contrato) {
        contratoRepository.save(contrato);

        return ResponseEntity.ok(contrato);
    }

    public ResponseEntity<List<Contrato>> listarContratos() {
        List<Contrato> listaContratos = contratoRepository.findAll();

        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<Contrato> buscarPorId (String id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontrado("Contrato nao encontrado com Id: " + id));

        return ResponseEntity.ok(contrato);
    }

    public ResponseEntity<Contrato> renovarContrato (String id, Contrato contrato) {
        Contrato contratoRenovado = contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontrado("Contrato nao encontrado com Id: " + id));

        contratoRenovado.setDataLocacao(contrato.getDataLocacao());
        contratoRenovado.setDataDevolucao(contrato.getDataDevolucao());
        contratoRenovado.setFormaPagamento(contrato.getFormaPagamento());
        contratoRenovado.setContratante(contrato.getContratante());
        contratoRenovado.setPecas(contrato.getPecas());
        contratoRenovado.setEndereco(contrato.getEndereco());

        contratoRepository.save(contratoRenovado);

        return ResponseEntity.ok(contratoRenovado);
    }

    public ResponseEntity<Contrato> excluirContrato (String id) {
        contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontrado("Contrato nao encontrado com Id: " + id));

        contratoRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}