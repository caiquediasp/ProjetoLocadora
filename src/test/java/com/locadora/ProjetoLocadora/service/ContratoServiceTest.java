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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    Contratante contratante = new Contratante("05433755363", "nome", "(xx)xxxxx-xxxx");
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
        assertThat(contratoService.listarTodosContratos()).isNotEmpty();
    }

    @Test
    void listarContratosDoContratante() {
        assertThat(contratoService.listarContratosDoContratante("05433755363")).isNotEmpty();
    }

    @Test
    void listarContratosDoEndereco() {
        List<Contrato> listaTeste = contratoService.listarContratosDoContratante("05433755363");
        Contrato c = listaTeste.get(0);
        assertThat(contratoService.listarContratosDoEndereco(c.getEndereco().getId())).isNotEmpty();
    }

    @Test
    void listarContratosAtivos() {
        assertThat(contratoService.listarContratosAtivos()).isNotEmpty();
    }

    @Test
    void listarContratosVencidos() {
        contrato.setStatus("VENCIDO");
        contratoService.adicionarContrato(contrato);

        assertThat(contratoService.listarContratosVencidos()).isNotEmpty();
    }

    @Test
    void buscarContratoPorId() {
        List<Contrato> listaTeste = contratoService.listarContratosDoContratante("05433755363");
        Contrato c = listaTeste.get(0);

        assertThat(contratoService.buscarContratoPorId(c.getId())).isNotNull();
    }

    @Test
    void renovarContrato() {
        List<Contrato> listaTeste = contratoService.listarContratosDoContratante("05433755363");
        Contrato c = listaTeste.get(0);
        pecas.getAndaime().setQtdAndaime(10);
        pecas.getEscora().setQtdEscora(15);

        assertThat(contratoService.renovarContrato(c.getId(), pecas,
                LocalDate.now().plusDays(10), LocalDate.now().plusDays(27), FormaPagamento.AVISTA)).isNotNull();
    }

    @Test
    void excluirContrato() {
        List<Contrato> listaTeste = contratoService.listarContratosDoContratante("05433755363");
        Contrato c = listaTeste.get(0);

        assertThat(contratoService.excluirContrato(c.getId())).isNotNull();
    }
}