package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.exceptions.ContratanteNaoEncontradoException;
import com.locadora.ProjetoLocadora.exceptions.ContratoNaoEncontradoException;
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
import org.springframework.web.server.ResponseStatusException;


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

    public ResponseEntity<Contrato> adicionarContrato(Contrato contrato) {
        contratoRepository.atualizarStatusContrato();

        cpfValidation.validadorCpf(contrato.getContratante().getCpf());

        Contratante contratante = contratanteRepository.findById(contrato.getContratante().getCpf())
                .orElse(null);

        if(contratante != null)
            contrato.setContratante(contratante);



        Endereco endereco = enderecoRepository.verificarEnderecoExistente(contrato.getEndereco().getCep()
                , contrato.getEndereco().getBairro()
                , contrato.getEndereco().getRua()
                , contrato.getEndereco().getNumero()
        );

        if(endereco != null)
            contrato.setEndereco(endereco);

        contrato.setValorTotal(contrato.getPecas().valorTotal());

        if(contrato.getFormaPagamento() == FormaPagamento.CREDITO)
            contrato.setValorTotal(contrato.getValorTotal() * 1.05);

        contrato.setStatus("ATIVO");

        contratoRepository.save(contrato);

        return ResponseEntity.ok(contrato);
    }

    public ResponseEntity<List<Contrato>> listarTodosContratos() {
        contratoRepository.atualizarStatusContrato();

        List<Contrato> listaContratos = contratoRepository.findAll();

        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<List<Contrato>> listarContratosDoContratante(String cpf) {
        contratoRepository.atualizarStatusContrato();

        cpfValidation.validadorCpf(cpf);

        contratanteRepository.findById(cpf)
                .orElseThrow(() -> new ContratanteNaoEncontradoException(cpf));

        List<Contrato> listaContratos = contratoRepository.listarContratosDoContratante(cpf);

        if (listaContratos.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND
                , "Nenhum contrato encontrado deste contratante!");


        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<List<Contrato>> listarContratosDoEndereco(String id) {
        contratoRepository.atualizarStatusContrato();

        enderecoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
                        , "Nenhum endereço encontrado com este id!"));

        List<Contrato> listaContratos = contratoRepository.listarContratosDoEndereco(id);

        if (listaContratos.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND
                    , "Nenhum contrato encontrado neste endereço!");


        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<List<Contrato>> listarContratosAtivos() {
        contratoRepository.atualizarStatusContrato();

        List<Contrato> listaContratos = contratoRepository.listarContratosAtivos();

        if(listaContratos.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND
                , "Nenhum contrato ativo!");


        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<List<Contrato>> listarContratosVencidos() {
        contratoRepository.atualizarStatusContrato();

        List<Contrato> listaContratos = contratoRepository.listarContratosVencidos();

        if (listaContratos.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND
                , "Nenhum contrato vencido!");


        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<Contrato> buscarContratoPorId (String id) {
        contratoRepository.atualizarStatusContrato();

        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontradoException(id));

        return ResponseEntity.ok(contrato);
    }

    public ResponseEntity<Contrato> renovarContrato (String id, Pecas pecas
            , LocalDate dataRenovacao, LocalDate dataDevolucao
            , FormaPagamento formaPagamento)
    {
        contratoRepository.atualizarStatusContrato();

        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontradoException(id));

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
                .orElseThrow(() -> new ContratoNaoEncontradoException(id));

        contratoRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
