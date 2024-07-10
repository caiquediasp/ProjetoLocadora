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
import org.springframework.http.ResponseEntity;

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
    private EnderecoRepository enderecoRepository;
    @Mock
    private ContratoService contratoService;
    @Mock
    private ContratanteService contratanteService;
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
    void adicionarContrato() throws  Exception{
        String cpf = "05433755363";
        cpfValidation.validadorCpf(cpf);
        estoqueValidation.verificaDisponibilidadePecas(pecas);

        when(contratanteService.buscarContratantePorCpf(cpf)).thenReturn(contratante);
        Contratante teste = contratanteService.buscarContratantePorCpf("05433755363");
        assertThat(teste).isNotNull();

        contratanteService.salvarContratante(teste);

        when(enderecoRepository.verificarEnderecoExistente(endereco.getCep(),
                endereco.getBairro(), endereco.getRua(), endereco.getNumero())).thenReturn(endereco);
        enderecoRepository.verificarEnderecoExistente(endereco.getCep(),
                endereco.getBairro(), endereco.getRua(), endereco.getNumero());
        verify(enderecoRepository, times(1)).verificarEnderecoExistente
                (endereco.getCep(), endereco.getBairro(), endereco.getRua(), endereco.getNumero());

        contrato.setValorTotal(contrato.getPecas().valorTotal());
        assertThat(contrato.getValorTotal()).isNotNull();

        contrato.setStatus("ATIVO");

        when(contratoService.adicionarContrato(contrato)).thenReturn(contrato);
        Contrato testeContrato = contratoService.adicionarContrato(contrato);
        assertThat(testeContrato).isNotNull();
    }

    @Test
    void listarTodosContratos() {

    }

    @Test
    void listarContratosDoContratante() {

    }

    @Test
    void listarContratosDoEndereco() {

    }

    @Test
    void listarContratosAtivos() {

    }

    @Test
    void listarContratosVencidos() {

    }

    @Test
    void buscarContratoPorId() {

    }

    @Test
    void renovarContrato() {
    }

    @Test
    void excluirContrato() {

    }
}