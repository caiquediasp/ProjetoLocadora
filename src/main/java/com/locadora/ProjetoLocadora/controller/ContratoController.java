package com.locadora.ProjetoLocadora.controller;

import com.locadora.ProjetoLocadora.exceptions.ContratoNaoEncontrado;
import com.locadora.ProjetoLocadora.repository.ContratoRepository;
import com.locadora.ProjetoLocadora.util.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locacao")
public class ContratoController {
    @Autowired
    private ContratoRepository contratoRepository;

    @PostMapping("/adicionarContrato")
    public ResponseEntity<Contrato> adicionarContrato(Contrato contrato) {
        contratoRepository.save(contrato);

        return ResponseEntity.ok(contrato);
    }

    @GetMapping("/listarContratos")
    public ResponseEntity<List<Contrato>> listarContratos() {
        List<Contrato> listaContratos = contratoRepository.findAll();

        return ResponseEntity.ok(listaContratos);
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Contrato> buscarPorId (@PathVariable("id") String id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontrado("Contrato nao encontrado com Id: " + id));

        return ResponseEntity.ok(contrato);
    }

    @PutMapping("/renovarContrato")
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

    @DeleteMapping("/excluirContrato/{id}")
    public ResponseEntity<Contrato> excluirContrato (@PathVariable("id") String id) {
        contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontrado("Contrato nao encontrado com Id: " + id));

        contratoRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
