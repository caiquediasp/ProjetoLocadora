package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.repository.ContratanteRepository;
import com.locadora.ProjetoLocadora.repository.ContratoRepository;
import com.locadora.ProjetoLocadora.repository.EnderecoRepository;
import com.locadora.ProjetoLocadora.repository.PecasRepository;
import com.locadora.ProjetoLocadora.util.*;
import com.locadora.ProjetoLocadora.util.pecas.Andaime;
import com.locadora.ProjetoLocadora.util.pecas.Escora;
import com.locadora.ProjetoLocadora.util.pecas.Plataforma;
import com.locadora.ProjetoLocadora.util.pecas.Roldana;
import com.locadora.ProjetoLocadora.validations.ContratoStatusValidation;
import com.locadora.ProjetoLocadora.validations.CpfValidation;
import com.locadora.ProjetoLocadora.validations.EstoqueValidation;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ContratoServiceTest {
    @Mock
    private ContratoRepository contratoRepository;
    @Mock
    private ContratanteRepository contratanteRepository;
    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private PecasRepository pecasRepository;
    @Mock
    private ContratoStatusValidation contratoStatusValidation;
    @Mock
    private EstoqueValidation estoqueValidation;
    @Mock
    private CpfValidation cpfValidation;

    Contratante contratante = new Contratante("05433755363", "teste", "(xx)xxxxx-xxxx");
    Endereco endereco = new Endereco("12345-678", "teste", "teste", 1234);
    Andaime andaime = new Andaime(5, 1);
    Escora escora = new Escora(5, 1);
    Plataforma plataforma = new Plataforma(5, 1);
    Roldana roldana = new Roldana(5);
    Pecas pecas = new Pecas(andaime, escora, plataforma, roldana);
    Contrato contrato = new Contrato(LocalDate.now(), LocalDate.now().plusDays(5L), FormaPagamento.PIX, endereco, contratante, pecas);

    @Test
    void adicionarContrato() {
        when(contratanteRepository.findById(contratante.getCpf())).thenReturn(Optional.of(contratante));
        contratanteRepository.findById(contratante.getCpf());
        verify(contratanteRepository, times(1)).findById(contratante.getCpf());

        when(contratanteRepository.save(contratante)).thenReturn(contratante);
        contratanteRepository.save(contratante);
        verify(contratanteRepository, times(1)).save(contratante);

        when(enderecoRepository.verificarEnderecoExistente(endereco.getCep(), endereco.getBairro(), endereco.getRua(), endereco.getNumero())).thenReturn(endereco);
        enderecoRepository.verificarEnderecoExistente(endereco.getCep(), endereco.getBairro(), endereco.getRua(), endereco.getNumero());
        verify(enderecoRepository, times(1)).verificarEnderecoExistente(
                endereco.getCep(), endereco.getBairro(), endereco.getRua(), endereco.getNumero());

        contrato.setValorTotal(contrato.getPecas().valorTotal());
        contrato.setStatus("ATIVO");

        when(contratoRepository.save(contrato)).thenReturn(contrato);
        contratoRepository.save(contrato);
        verify(contratoRepository, times(1)).save(contrato);

    }

    @Test
    void listarTodosContratos() {
        when(contratoRepository.findAll()).thenReturn(null);
        contratoRepository.findAll();
        verify(contratoRepository, times(1)).findAll();
    }

    @Test
    void listarContratosDoContratante() {
        cpfValidation.validadorCpf(contratante.getCpf());

        when(contratanteRepository.findById(contratante.getCpf())).thenReturn(Optional.ofNullable(contratante));
        contratanteRepository.findById(contratante.getCpf());
        verify(contratanteRepository, times(1)).findById(contratante.getCpf());

        when(contratoRepository.listarContratosDoContratante(contratante.getCpf())).thenReturn(null);
        contratoRepository.listarContratosDoContratante(contratante.getCpf());
        verify(contratoRepository, times(1)).listarContratosDoContratante(contratante.getCpf());
    }

    @Test
    void listarContratosDoEndereco() {
        when(contratoRepository.listarContratosDoEndereco(endereco.getId())).thenReturn(null);
        contratoRepository.listarContratosDoEndereco(endereco.getId());
        verify(contratoRepository, times(1)).listarContratosDoEndereco(endereco.getId());
    }

    @Test
    void listarContratosAtivos() {
        when(contratoRepository.listarContratosAtivos()).thenReturn(null);
        contratoRepository.listarContratosAtivos();
        verify(contratoRepository, times(1)).listarContratosAtivos();
    }

    @Test
    void listarContratosVencidos() {
        when(contratoRepository.listarContratosVencidos()).thenReturn(null);
        contratoRepository.listarContratosVencidos();
        verify(contratoRepository, times(1)).listarContratosVencidos();
    }

    @Test
    void buscarContratoPorId() {
        when(contratoRepository.findById(contrato.getId())).thenReturn(null);
        contratoRepository.findById(contrato.getId());
        verify(contratoRepository, times(1)).findById(contrato.getId());
    }

    @Test
    void renovarContrato() {
    }

    @Test
    void excluirContrato() {
        when(contratoRepository.findById(contrato.getId())).thenReturn(Optional.ofNullable(contrato));
        contratoRepository.findById(contrato.getId());
        verify(contratoRepository, times(1)).findById(contrato.getId());

        contratoRepository.deleteById(contrato.getId());
        verify(contratoRepository, times(1)).deleteById(contrato.getId());
    }
}