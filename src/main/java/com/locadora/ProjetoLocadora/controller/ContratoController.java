package com.locadora.ProjetoLocadora.controller;


import com.locadora.ProjetoLocadora.service.ContratoService;
import com.locadora.ProjetoLocadora.util.Contrato;
import com.locadora.ProjetoLocadora.util.FormaPagamento;
import com.locadora.ProjetoLocadora.util.Pecas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/locacao/contrato")
public class ContratoController {
    @Autowired
    private ContratoService contratoService;

    @PostMapping("/adicionarContrato")
    public ResponseEntity<Object> adicionarContrato(@RequestBody Contrato contrato) throws Exception{
        return contratoService.adicionarContrato(contrato);
    }

    @GetMapping("/listarTodosContratos")
    public ResponseEntity<List<Contrato>> listarTodosContratos() {
        return contratoService.listarTodosContratos();
    }
    @GetMapping("/listarContratosDoContratante/{cpf}")
    public ResponseEntity<Object> listarContratosDoContratante(@PathVariable("cpf") String cpf) throws Exception{
        return contratoService.listarContratosDoContratante(cpf);
    }

    @GetMapping("/listarContratosDoEndereco/{id}")
    public ResponseEntity<Object> listarContratosDoEndereco(@PathVariable("id") String id) {
        return contratoService.listarContratosDoEndereco(id);
    }

    @GetMapping("/listarContratosAtivos")
    public ResponseEntity<Object> listarContratosAtivos() {
        return contratoService.listarContratosAtivos();
    }

    @GetMapping("/listarContratosVencidos")
    public ResponseEntity<Object> listarContratosVencidos() {
        return contratoService.listarContratosVencidos();
    }

    @GetMapping("/buscarPorId/{id}")
    public ResponseEntity<Contrato> buscarContratoPorId (@PathVariable("id") String id) {
        return contratoService.buscarContratoPorId(id);
    }

    @PutMapping("/renovarContrato")
    public ResponseEntity<Contrato> renovarContrato (String id, @RequestBody Pecas pecas, LocalDate dataRenovacao, LocalDate dataDevolucao, FormaPagamento formaPagamento) {
        return contratoService.renovarContrato(id, pecas, dataRenovacao, dataDevolucao, formaPagamento);
    }

    @DeleteMapping("/excluirContrato/{id}")
    public ResponseEntity<Contrato> excluirContrato (@PathVariable("id") String id) {
        return contratoService.excluirContrato(id);
    }
}
