package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.exceptions.ContratanteNaoEncontradoException;
import com.locadora.ProjetoLocadora.exceptions.ContratoNaoEncontradoException;
import com.locadora.ProjetoLocadora.exceptions.QuantidadeInvalidaException;
import com.locadora.ProjetoLocadora.repository.*;
import com.locadora.ProjetoLocadora.util.*;
import com.locadora.ProjetoLocadora.validations.ContratoStatusValidation;
import com.locadora.ProjetoLocadora.validations.CpfValidation;
import com.locadora.ProjetoLocadora.validations.EstoqueValidation;
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
    private ContratoStatusValidation contratoStatusValidation;
    @Autowired
    private EstoqueValidation estoqueValidation;
    @Autowired
    private CpfValidation cpfValidation;

    public ResponseEntity<Contrato> adicionarContrato(Contrato contrato) {
        contratoStatusValidation.validaStatusContrato();

        cpfValidation.validadorCpf(contrato.getContratante().getCpf());

        estoqueValidation.verificaDisponibilidadePecas(contrato.getPecas());

        Contratante contratante = contratanteRepository.findById(contrato.getContratante().getCpf())
                .orElse(null);

        if(contratante != null)
            contrato.setContratante(contratante);

        Endereco endereco = enderecoRepository.verificarEnderecoExistente(
                contrato.getEndereco().getCep()
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
        contratoStatusValidation.validaStatusContrato();

        List<Contrato> listaContratos = contratoRepository.findAll();

        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<List<Contrato>> listarContratosDoContratante(String cpf) {
        contratoStatusValidation.validaStatusContrato();

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
        contratoStatusValidation.validaStatusContrato();

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
        contratoStatusValidation.validaStatusContrato();

        List<Contrato> listaContratos = contratoRepository.listarContratosAtivos();

        if(listaContratos.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND
                , "Nenhum contrato ativo!");


        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<List<Contrato>> listarContratosVencidos() {
        contratoStatusValidation.validaStatusContrato();

        List<Contrato> listaContratos = contratoRepository.listarContratosVencidos();

        if (listaContratos.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND
                , "Nenhum contrato vencido!");


        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<Contrato> buscarContratoPorId (String id) {
        contratoStatusValidation.validaStatusContrato();

        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontradoException(id));

        return ResponseEntity.ok(contrato);
    }

    public ResponseEntity<Contrato> renovarContrato (String id, Pecas pecas
            , LocalDate dataRenovacao, LocalDate dataDevolucao
            , FormaPagamento formaPagamento)
    {
        contratoStatusValidation.validaStatusContrato();

        estoqueValidation.verificaDisponibilidadePecas(pecas);

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
        contratoStatusValidation.validaStatusContrato();

        contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontradoException(id));

        contratoRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
