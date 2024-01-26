package com.locadora.ProjetoLocadora.controller;

import com.locadora.ProjetoLocadora.service.ContratanteService;
import com.locadora.ProjetoLocadora.util.Contratante;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locacao/contratante")
public class ContratanteController {
    @Autowired
    ContratanteService contratanteService;

    @GetMapping("/listarTodosContratantes")
    public ResponseEntity<List<Contratante>> listarTodosContratantes() {
        return contratanteService.listarTodosContratantes();
    }

    @GetMapping("/buscarContratantePorCpf/{cpf}")
    public ResponseEntity<Contratante> buscarContratantePorCpf(@PathVariable("cpf") String cpf) {
        return contratanteService.buscarContratantePorCpf(cpf);
    }

    @GetMapping("/quantidadeContratoDoContratante/{cpf}")
    public ResponseEntity<Integer> quantidadeContratoDoContratante(@PathVariable("cpf") String cpf) {
        return contratanteService.quantidadeContratoDoContratante(cpf);
    }

    @PutMapping("/atualizarTelefone/{cpf}")
    public ResponseEntity<Contratante> atualizarTelefone(@PathVariable("cpf") String cpf, String telefone){
        return contratanteService.atualizarTelefone(cpf, telefone);
    }
}
