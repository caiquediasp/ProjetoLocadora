package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.repository.ContratanteRepository;
import com.locadora.ProjetoLocadora.repository.ContratoRepository;
import com.locadora.ProjetoLocadora.repository.EnderecoRepository;
import com.locadora.ProjetoLocadora.util.*;
import com.locadora.ProjetoLocadora.util.pecas.Andaime;
import com.locadora.ProjetoLocadora.util.pecas.Escora;
import com.locadora.ProjetoLocadora.util.pecas.Plataforma;
import com.locadora.ProjetoLocadora.util.pecas.Roldana;
import com.locadora.ProjetoLocadora.validations.ContratoStatusValidation;
import com.locadora.ProjetoLocadora.validations.CpfValidation;
import com.locadora.ProjetoLocadora.validations.EstoqueValidation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatList;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ContratoServiceTest {
    @InjectMocks
    private ContratoService contratoService;
    @Mock
    private ContratoStatusValidation contratoStatusValidation;
    @Mock
    private CpfValidation cpfValidation;
    @Mock
    private EstoqueValidation estoqueValidation;
    @Mock
    private ContratanteRepository contratanteRepository;
    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private ContratoRepository contratoRepository;

    Contratante contratante = new Contratante("42145065024", "nome", "(xx)xxxxx-xxxx");
    Endereco endereco = new Endereco("12345-678", "bairro", "rua", 1234);
    Andaime andaime = new Andaime(5, 1);
    Escora escora = new Escora(5, 1);
    Plataforma plataforma = new Plataforma(5, 1);
    Roldana roldana = new Roldana(5);
    Pecas pecas = new Pecas(andaime, escora, plataforma, roldana);
    Contrato contrato = new Contrato(LocalDate.now(), LocalDate.now().plusDays(5L), FormaPagamento.PIX, endereco, contratante, pecas);

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        contrato.setStatus("ATIVO");
        contrato.setValorTotal(pecas.valorTotal());
    }

    @Test
    void adicionarContrato() {
        when(enderecoRepository.verificarEnderecoExistente(anyString(), anyString(), anyString(), anyInt())).thenReturn(endereco);
        when(contratanteRepository.findById(contratante.getCpf())).thenReturn(Optional.of(contratante));

        Contrato resultado = contratoService.adicionarContrato(contrato);

        verify(contratoStatusValidation).validaStatusContrato();
        verify(cpfValidation).validadorCpf(contratante.getCpf());
        verify(estoqueValidation).verificaDisponibilidadePecas(pecas);
        verify(contratanteRepository).findById(contratante.getCpf());
        verify(contratanteRepository).save(contratante);
        verify(enderecoRepository).verificarEnderecoExistente(endereco.getCep(), endereco.getBairro(), endereco.getRua(), endereco.getNumero());
        verify(contratoRepository).save(contrato);

        assertNotNull(resultado);
    }

    @Test
    void listarTodosContratos() {
        List<Contrato> listaTeste = new ArrayList<Contrato>();
        listaTeste.add(contrato);
        when(contratoRepository.findAll()).thenReturn(listaTeste);

        List<Contrato> listaResultado = contratoService.listarTodosContratos();

        verify(contratoRepository).findAll();
        assertThatList(listaResultado).isNotEmpty();
    }

    @Test
    void listarContratosDoContratante() {
        List<Contrato> listaTeste = new ArrayList<Contrato>();
        listaTeste.add(contrato);
        when(contratoRepository.listarContratosDoContratante(contratante.getCpf())).thenReturn(listaTeste);

        List<Contrato> listaResultado = contratoService.listarContratosDoContratante(contratante.getCpf());

        verify(contratoRepository).listarContratosDoContratante(contratante.getCpf());
        assertThatList(listaResultado).isNotEmpty();
    }

    @Test
    void listarContratosDoEndereco() {
        List<Contrato> listaTeste = new ArrayList<Contrato>();
        listaTeste.add(contrato);
        when(contratoRepository.listarContratosDoEndereco(contrato.getEndereco().getId())).thenReturn(listaTeste);

        List<Contrato> listaResultado = contratoService.listarContratosDoEndereco(contrato.getEndereco().getId());

        verify(contratoRepository).listarContratosDoEndereco(contrato.getEndereco().getId());
        assertThatList(listaResultado).isNotEmpty();
    }

    @Test
    void listarContratosAtivos() {
        List<Contrato> listaTeste = new ArrayList<Contrato>();
        listaTeste.add(contrato);
        when(contratoRepository.listarContratosAtivos()).thenReturn(listaTeste);

        List<Contrato> listaResultado = contratoService.listarContratosAtivos();

        verify(contratoRepository).listarContratosAtivos();
        assertThatList(listaResultado).isNotEmpty();
    }

    @Test
    void listarContratosVencidos() {
        List<Contrato> listaTeste = new ArrayList<Contrato>();
        listaTeste.add(contrato);
        when(contratoRepository.listarContratosVencidos()).thenReturn(listaTeste);

        List<Contrato> listaResultado = contratoService.listarContratosVencidos();

        verify(contratoRepository).listarContratosVencidos();
        assertThatList(listaResultado).isNotEmpty();
    }

    @Test
    void buscarContratoPorId() {
        when(contratoRepository.findById(contrato.getId())).thenReturn(Optional.ofNullable(contrato));

        Contrato resultado = contratoService.buscarContratoPorId(contrato.getId());

        verify(contratoRepository).findById(contrato.getId());
        assertNotNull(resultado);
    }

    @Test
    void renovarContrato() {
        
    }

    @Test
    void excluirContrato() {

    }
}