package com.locadora.ProjetoLocadora.controller;


import com.locadora.ProjetoLocadora.service.ContratoService;
import com.locadora.ProjetoLocadora.entities.Contrato;
import com.locadora.ProjetoLocadora.entities.FormaPagamento;
import com.locadora.ProjetoLocadora.entities.Pecas;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/contrato")
@Tag(name = "Contrato", description = "Métodos referentes à entidade Contrato")
public class ContratoController {
    @Autowired
    private ContratoService contratoService;

    @PostMapping("/adicionar")
    @Operation(summary = "Adicionar novo contrato", description = "Registra um novo contrato no banco de dados")
    public ResponseEntity<Contrato> adicionarContrato(@RequestBody Contrato contrato) {
        return ResponseEntity.ok(contratoService.adicionarContrato(contrato));
    }

    @GetMapping("/listarTodos")
    @Operation(summary = "Listar todos os contratos", description = "Retorna uma lista com todos os contratos")
    public ResponseEntity<List<Contrato>> listarTodosContratos() {
        return ResponseEntity.ok(contratoService.listarTodosContratos());
    }

    @GetMapping("/listarPorContratante/{cpf}")
    @Operation(summary = "Lista contratos por Contratante", description = "Retorna uma lista de todos os contratos com o mesmo contratante")
    public ResponseEntity<List<Contrato>> listarContratosDoContratante(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(contratoService.listarContratosDoContratante(cpf));
    }

    @GetMapping("/listarPorEndereco/{id}")
    @Operation(summary = "Lista contratos por endereço", description = "Retorna uma lista de todos os contratos com o mesmo endereço")
    public ResponseEntity<List<Contrato>> listarContratosDoEndereco(@PathVariable("id") String id) {
        return ResponseEntity.ok(contratoService.listarContratosDoEndereco(id));
    }

    @GetMapping("/listarAtivos")
    @Operation(summary = "Lista contratos ativos", description = "Retorna uma lista com todos os contratos ativos")
    public ResponseEntity<List<Contrato>> listarContratosAtivos() {
        return ResponseEntity.ok(contratoService.listarContratosAtivos());
    }

    @GetMapping("/listarVencidos")
    @Operation(summary = "Listar contratos vencidos", description = "Retorna uma lista com todos os contratos vencidos")
    public ResponseEntity<List<Contrato>> listarContratosVencidos() {
        return ResponseEntity.ok(contratoService.listarContratosVencidos());
    }

    @GetMapping("/buscarPorId/{id}")
    @Operation(summary = "Buscar contrato por Id", description = "Retorna um contrato a partir de um Id")
    public ResponseEntity<Contrato> buscarContratoPorId (@PathVariable("id") String id) {
        return ResponseEntity.ok(contratoService.buscarContratoPorId(id));
    }

    @PatchMapping("/renovar/{id}")
    @Operation(summary = "Renovar contrato", description = "Renova um contrato a partir de um Id")
    public ResponseEntity<Contrato> renovarContrato (@PathVariable("id") String id, @RequestBody Pecas pecas
            , LocalDate dataRenovacao, LocalDate dataDevolucao
            , FormaPagamento formaPagamento)
    {
        return ResponseEntity.ok(contratoService.renovarContrato(id, pecas, dataRenovacao, dataDevolucao, formaPagamento));
    }

    @DeleteMapping("/excluir/{id}")
    @Operation(summary = "Excluir contrato", description = "Exclui um contrato a partir de um Id")
    public ResponseEntity excluirContrato (@PathVariable("id") String id) {
        return contratoService.excluirContrato(id);
    }
}
