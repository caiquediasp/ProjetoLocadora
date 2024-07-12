package com.locadora.ProjetoLocadora.service;

import com.locadora.ProjetoLocadora.util.*;
import com.locadora.ProjetoLocadora.util.pecas.Andaime;
import com.locadora.ProjetoLocadora.util.pecas.Escora;
import com.locadora.ProjetoLocadora.util.pecas.Plataforma;
import com.locadora.ProjetoLocadora.util.pecas.Roldana;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ContratoServiceTest {
    @Autowired
    private ContratoService contratoService;

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
        assertThat(contratoService.adicionarContrato(contrato)).isNotNull();
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

    }
}