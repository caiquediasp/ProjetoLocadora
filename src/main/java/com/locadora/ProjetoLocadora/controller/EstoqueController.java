package com.locadora.ProjetoLocadora.controller;

import com.locadora.ProjetoLocadora.service.EstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estoque")
@Tag(name = "Estoque de Peças", description = "Métodos referentes ao controle do Estoque das Peças")
public class EstoqueController {
    @Autowired
    private EstoqueService estoqueService;

    @GetMapping("/andaime")
    @Operation(summary = "Estoque de andaimes", description = "Verifica no banco de dados o estoque de andaimes, de acordo com o tamanho informado")
    public ResponseEntity<Integer> verificaEstoqueAndaime(int tamanho) {
        return estoqueService.verificaEstoqueAndaime(tamanho);
    }

    @GetMapping("/escora")
    @Operation(summary = "Estoque de escoras", description = "Verifica no banco de dados o estoque de escoras, de acordo com o tamanho informado")
    public ResponseEntity<Integer> verificaEstoqueEscora(int tamanho) {
        return estoqueService.verificaEstoqueEscora(tamanho);
    }

    @GetMapping("/plataforma")
    @Operation(summary = "Estoque de plataformas", description = "Verifica no banco de dados o estoque de plataformas, de acordo com o tamanho informado")
    public ResponseEntity<Integer> verificaEstoquePlataforma(int tamanho) {
        return estoqueService.verificaEstoquePlataforma(tamanho);
    }

    @GetMapping("/roldana")
    @Operation(summary = "Estoque de roldanas", description = "Verifica no banco de dados o estoque de roldanas, que possui um tamanho padrão")
    public ResponseEntity<Integer> verificaEstoqueRoldana() {
        return estoqueService.verificaEstoqueRoldana();
    }
}
