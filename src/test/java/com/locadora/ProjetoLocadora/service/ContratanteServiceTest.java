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
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ContratanteServiceTest {
    @InjectMocks
    private ContratanteService contratanteService;
    @Mock
    private CpfValidation cpfValidation;
    @Mock
    private ContratanteRepository contratanteRepository;
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
        contratoRepository.save(contrato);
    }

    @Test
    void listarTodosContratantes() {
        List<Contratante> listaTeste = new ArrayList<Contratante>();
        listaTeste.add(contratante);
        when(contratanteRepository.findAll()).thenReturn(listaTeste);

        List<Contratante> listaContratante = contratanteService.listarTodosContratantes();

        verify(contratanteRepository).findAll();
        assertThat(listaContratante).isNotEmpty();
    }

    @Test
    void buscarContratantePorCpf() {
        String cpf = "42145065024";
        when(contratanteRepository.findById(cpf)).thenReturn(Optional.ofNullable(contratante));

        Contratante resultado = contratanteService.buscarContratantePorCpf(cpf);

        verify(cpfValidation).validadorCpf(cpf);
        verify(contratanteRepository).findById(cpf);
        assertNotNull(resultado);
    }

    @Test
    void quantidadeContratoDoContratante() {

    }

    @Test
    void atualizarTelefone() {
        String cpf = "42145065024";
        String telefone = "(11)11111-1111";
        when(contratanteRepository.findById(cpf)).thenReturn(Optional.ofNullable(contratante));
        when(contratanteRepository.save(contratante)).thenReturn(contratante);

        Contratante resultado = contratanteService.atualizarTelefone(cpf, telefone);

        verify(cpfValidation).validadorCpf(cpf);
        verify(contratanteRepository).findById(cpf);
        verify(contratanteRepository).save(contratante);
        assertNotNull(resultado);
    }
}