package com.locadora.ProjetoLocadora.controller;

import com.locadora.ProjetoLocadora.service.ContratanteService;
import com.locadora.ProjetoLocadora.util.Contratante;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contratante")
@Tag(name = "Contratante", description = "Métodos referentes à entidade Contratante")
public class ContratanteController {
    @Autowired
    ContratanteService contratanteService;

    @GetMapping("/listarTodos")
    @Operation(summary = "Listar contratantes", description = "Retorna uma lista com todos os contratantes")
    public ResponseEntity<List<Contratante>> listarTodosContratantes() {
        return contratanteService.listarTodosContratantes();
    }

    @GetMapping("buscarPorCpf/{cpf}")
    @Operation(summary = "Buscar contratante por CPF", description = "Retorna um contratante a partir de um CPF")
    public ResponseEntity<Contratante> buscarContratantePorCpf(@PathVariable("cpf") String cpf) {
        return contratanteService.buscarContratantePorCpf(cpf);
    }

    @GetMapping("/qtdPorContratante/{cpf}")
    @Operation(summary = "Quantidade de contratos do contratante", description = "Retorna o número de contratos de um contratante a partir de um CPF")
    public ResponseEntity<Integer> quantidadeContratoDoContratante(@PathVariable("cpf") String cpf) {
        return contratanteService.quantidadeContratoDoContratante(cpf);
    }

    @PatchMapping("/atualizarTelefone/{cpf}")
    @Operation(summary = "Atualizar telefone", description = "Atualiza o telefone de um contratante a partir de um CPF")
    public ResponseEntity<Contratante> atualizarTelefone(@PathVariable("cpf") String cpf, String telefone){
        return contratanteService.atualizarTelefone(cpf, telefone);
    }
}
