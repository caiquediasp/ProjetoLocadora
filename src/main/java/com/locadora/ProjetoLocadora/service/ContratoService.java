package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.dao.ContratoDAO;
import com.locadora.ProjetoLocadora.dao.EnderecoDAO;
import com.locadora.ProjetoLocadora.repository.ContratanteRepository;
import com.locadora.ProjetoLocadora.repository.ContratoRepository;
import com.locadora.ProjetoLocadora.repository.EnderecoRepository;
import com.locadora.ProjetoLocadora.repository.PecasRepository;
import com.locadora.ProjetoLocadora.util.Contratante;
import com.locadora.ProjetoLocadora.util.Contrato;
import com.locadora.ProjetoLocadora.util.Endereco;
import com.locadora.ProjetoLocadora.util.FormaPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoService {
    @Autowired
    private ContratoDAO contratoDAO;
    @Autowired
    private EnderecoDAO enderecoDAO;
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

        Endereco endereco = enderecoDAO.verificarEnderecoExistente(contrato.getEndereco());

        if(contratante != null) {
            contrato.setContratante(contratante);
        }

        if(endereco != null) {
            contrato.setEndereco(endereco);
        }

        contrato.getPecas().getAndaime().valorTotal();
        contrato.getPecas().getEscora().valorTotal();
        contrato.getPecas().getPlataforma().valorTotal();
        contrato.getPecas().getRoldana().valorTotal();

        contrato.setValorTotal(contrato.getPecas().valorTotal());
        if(contrato.getFormaPagamento() != FormaPagamento.CREDITO) {
            contrato.setValorTotal(contrato.getValorTotal() * 0.95);
        }

        return contratoDAO.adicionarContrato(contrato);
    }

    public ResponseEntity<List<Contrato>> listarContratos() {
        return contratoDAO.listarContratos();
    }

    public ResponseEntity<Contrato> buscarPorId (String id) {
        return contratoDAO.buscarPorId(id);
    }

    public ResponseEntity<Contrato> renovarContrato (String id, Contrato contratoRenovado) {
        Contrato contrato = contratoDAO.buscarPorId(id).getBody();

        contrato.getPecas().getAndaime().valorTotal();
        contrato.getPecas().getEscora().valorTotal();
        contrato.getPecas().getPlataforma().valorTotal();
        contrato.getPecas().getRoldana().valorTotal();

        contrato.setValorTotal(contratoRenovado.getPecas().valorTotal());
        if(contrato.getFormaPagamento() != FormaPagamento.CREDITO) {
            contrato.setValorTotal(contratoRenovado.getValorTotal() * 0.95);
        }

        return contratoDAO.adicionarContrato(contrato);
    }

    public ResponseEntity<Contrato> excluirContrato (String id) {
        return contratoDAO.excluirContrato(id);
    }
}
