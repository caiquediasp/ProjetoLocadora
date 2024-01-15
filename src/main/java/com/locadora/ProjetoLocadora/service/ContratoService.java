package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.exceptions.ContratoNaoEncontrado;
import com.locadora.ProjetoLocadora.repository.ContratanteRepository;
import com.locadora.ProjetoLocadora.repository.ContratoRepository;
import com.locadora.ProjetoLocadora.repository.EnderecoRepository;
import com.locadora.ProjetoLocadora.repository.PecasRepository;
import com.locadora.ProjetoLocadora.util.Contratante;
import com.locadora.ProjetoLocadora.util.Contrato;
import com.locadora.ProjetoLocadora.util.Endereco;
import com.locadora.ProjetoLocadora.util.FormaPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<Contrato> adicionarContrato(Contrato contrato) {
        Contratante contratante = contratanteRepository.findById(contrato.getContratante().getCpf()).orElse(null);

        if(contratante != null) {
            contrato.setContratante(contratante);
        }

        Endereco endereco = enderecoRepository.verificarEnderecoExistente(contrato.getEndereco().getCep()
                , contrato.getEndereco().getBairro()
                , contrato.getEndereco().getRua()
                , contrato.getEndereco().getNumero()
        );

        if(endereco != null) {
            contrato.setEndereco(endereco);
        }

        contrato.setValorTotal(contrato.getPecas().valorTotal());
        if(contrato.getFormaPagamento() == FormaPagamento.CREDITO) {
            contrato.setValorTotal(contrato.getValorTotal() * 1.05);
        }

        contratoRepository.save(contrato);

        return ResponseEntity.ok(contrato);
    }

    public ResponseEntity<List<Contrato>> listarTodosContratos() {
        List<Contrato> listaContratos = contratoRepository.findAll();

        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<List<Contrato>> listarContratosPorContratante(String cpf) {
        List<Contrato> listaContratos = contratoRepository.listarContratosDoContratante(cpf);

        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<List<Contrato>> listarContratosAtivos() {
        List<Contrato> listaContratos = contratoRepository.listarContratosAtivos();

        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<List<Contrato>> listarContratosVencidos() {
        List<Contrato> listaContratos = contratoRepository.listarContratosVencidos();

        return ResponseEntity.ok(listaContratos);
    }

    public ResponseEntity<Contrato> buscarPorId (String id) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontrado("Contrato não encontrado com Id: " + id));

        return ResponseEntity.ok(contrato);
    }

    public ResponseEntity<Contrato> renovarContrato (String id, Contrato contratoRenovado) {
        Contrato contrato = contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontrado("Contrato não encontrado com Id: " + id));

        contrato.setContratante(contratoRenovado.getContratante());
        contrato.setEndereco(contratoRenovado.getEndereco());
        contrato.setPecas(contratoRenovado.getPecas());
        contrato.setDataLocacao(contratoRenovado.getDataLocacao());
        contrato.setDataDevolucao(contratoRenovado.getDataDevolucao());
        contrato.setFormaPagamento(contratoRenovado.getFormaPagamento());
        contrato.setValorTotal(contrato.getPecas().valorTotal());

        contratoRepository.save(contrato);

        return ResponseEntity.ok(contrato);
    }

    public ResponseEntity<Contrato> excluirContrato (String id) {
        contratoRepository.findById(id)
                .orElseThrow(() -> new ContratoNaoEncontrado("Contrato não encontrado com Id: " + id));

        contratoRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
