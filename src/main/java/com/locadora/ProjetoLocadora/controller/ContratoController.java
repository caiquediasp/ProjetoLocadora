package com.locadora.ProjetoLocadora.controller;


import com.locadora.ProjetoLocadora.service.ContratoService;
import com.locadora.ProjetoLocadora.util.Contrato;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locacao")
public class ContratoController {
    @Autowired
    private ContratoService contratoService;

    @PostMapping("/adicionarContrato")
    public ResponseEntity<Contrato> adicionarContrato(@RequestBody Contrato contrato) {
        return contratoService.adicionarContrato(contrato);
    }

    @GetMapping("/listarContratos")
    public ResponseEntity<List<Contrato>> listarContratos() {
        return contratoService.listarContratos();
    }

    @GetMapping("/listarContratosPorContratante/{cpf}")
    public ResponseEntity<List<Contrato>> listarContratosPorContratante(@PathVariable("cpf") String cpf) {
        return contratoService.listarContratosPorContratante(cpf);
    }

    @GetMapping("/listarContratosAtivos")
    public ResponseEntity<List<Contrato>> listarContratosAtivos() {
        return contratoService.listarContratosAtivos();
    }

    @GetMapping("/listarContratosVencidos")
    public ResponseEntity<List<Contrato>> listarContratosVencidos() {
        return contratoService.listarContratosVencidos();
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Contrato> buscarPorId (@PathVariable("id") String id) {
        return contratoService.buscarPorId(id);
    }

    @PutMapping("/renovarContrato")
    public ResponseEntity<Contrato> renovarContrato (String id, @RequestBody Contrato contrato) {
        return contratoService.renovarContrato(id, contrato);
    }

    @DeleteMapping("/excluirContrato/{id}")
    public ResponseEntity<Contrato> excluirContrato (@PathVariable("id") String id) {
        return contratoService.excluirContrato(id);
    }
}
