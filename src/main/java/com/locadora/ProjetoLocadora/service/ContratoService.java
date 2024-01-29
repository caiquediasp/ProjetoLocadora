package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.exceptions.ContratoNaoEncontradoException;
import com.locadora.ProjetoLocadora.exceptions.CpfInvalidoException;
import com.locadora.ProjetoLocadora.repository.ContratanteRepository;
import com.locadora.ProjetoLocadora.repository.ContratoRepository;
import com.locadora.ProjetoLocadora.repository.EnderecoRepository;
import com.locadora.ProjetoLocadora.repository.PecasRepository;
import com.locadora.ProjetoLocadora.util.*;
import com.locadora.ProjetoLocadora.validations.CpfValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
public class ContratoService {
    @Autowired
    private ContratoRepository contratoRepository;
    @Autowired
    private ContratanteRepository contratanteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private PecasRepository pecasRepository;
    @Autowired
    CpfValidation cpfValidation;

    public ResponseEntity<Object> adicionarContrato(Contrato contrato) {
        contratoRepository.atualizarStatusContrato();

        try{
            cpfValidation.validadorCpf(contrato.getContratante().getCpf());
        }
        catch (CpfInvalidoException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }

        Contratante contratante = contratanteRepository.findById(contrato.getContratante().getCpf())
                .orElse(null);

        if(contratante != null) contrato.setContratante(contratante);

        Endereco endereco = enderecoRepository.verificarEnderecoExistente(contrato.getEndereco().getCep()
                , contrato.getEndereco().getBairro()
                , contrato.getEndereco().getRua()
                , contrato.getEndereco().getNumero()
        );

        if(endereco != null) contrato.setEndereco(endereco);

        contrato.setValorTotal(contrato.getPecas().valorTotal());

        if(contrato.getFormaPagamento() == FormaPagamento.CREDITO) contrato.setValorTotal(contrato.getValorTotal() * 1.05);

        contrato.setStatus("ATIVO");

        contratoRepository.save(contrato);

        return ResponseEntity.ok(contrato);
    }

    public ResponseEntity<List<Contrato>> listarTodosContratos() {
        contratoRepository.atualizarStatusContrato();

        List<Contrato> listaContratos = contratoRepository.findAll();

        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<Object> listarContratosDoContratante(String cpf) {
        contratoRepository.atualizarStatusContrato();

        try{
            cpfValidation.validadorCpf(cpf);
        }
        catch (CpfInvalidoException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }

        List<Contrato> listaContratos = contratoRepository.listarContratosDoContratante(cpf);

        if (listaContratos.isEmpty()) return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("O contratante não possui contratos");


        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<Object> listarContratosDoEndereco(String id) {
        contratoRepository.atualizarStatusContrato();

        List<Contrato> listaContratos = contratoRepository.listarContratosDoEndereco(id);

        if (listaContratos.isEmpty()) return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("O endereço não possui contratos");


        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<Object> listarContratosAtivos() {
        contratoRepository.atualizarStatusContrato();

        List<Contrato> listaContratos = contratoRepository.listarContratosAtivos();

        if(listaContratos.isEmpty()) return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Não há nenhum contrato ativo!");


        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<Object> listarContratosVencidos() {
        contratoRepository.atualizarStatusContrato();

        List<Contrato> listaContratos = contratoRepository.listarContratosVencidos();

        if (listaContratos.isEmpty()) return ResponseEntity
                .status(HttpStatus.OK)
                .body("Não há nenhum contrato vencido!");


        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<Contrato> buscarContratoPorId (String id) {
        contratoRepository.atualizarStatusContrato();

        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontradoException("Contrato não encontrado com Id: " + id));

        return ResponseEntity.ok(contrato);
    }

    public ResponseEntity<Contrato> renovarContrato (String id, Pecas pecas, LocalDate dataRenovacao, LocalDate dataDevolucao, FormaPagamento formaPagamento) {
        contratoRepository.atualizarStatusContrato();

        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontradoException("Contrato não encontrado com Id: " + id));

        contrato.setPecas(pecas);
        contrato.setDataLocacao(dataRenovacao);
        contrato.setDataDevolucao(dataDevolucao);
        contrato.setFormaPagamento(formaPagamento);
        contrato.setValorTotal(pecas.valorTotal());
        contrato.setStatus("ATIVO");

        if(contrato.getFormaPagamento() == FormaPagamento.CREDITO) {
            contrato.setValorTotal(contrato.getValorTotal() * 1.05);
        }

        contratoRepository.save(contrato);

        return ResponseEntity.ok(contrato);
    }

    public ResponseEntity<Contrato> excluirContrato (String id) {
        contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontradoException("Contrato não encontrado com Id: " + id));

        contratoRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
