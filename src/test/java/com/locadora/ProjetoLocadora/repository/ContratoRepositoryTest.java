package com.locadora.ProjetoLocadora.repository;

import com.locadora.ProjetoLocadora.util.*;
import com.locadora.ProjetoLocadora.util.pecas.Andaime;
import com.locadora.ProjetoLocadora.util.pecas.Escora;
import com.locadora.ProjetoLocadora.util.pecas.Plataforma;
import com.locadora.ProjetoLocadora.util.pecas.Roldana;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ContratoRepositoryTest {
    @Autowired
    public ContratoRepository contratoRepository;

    @BeforeEach
    void setup() {
        Contratante contratante = new Contratante("42145065024", "teste", "(xx)xxxxx-xxxx");
        Endereco endereco = new Endereco("12345-678", "teste", "teste", 1234);
        Andaime andaime = new Andaime(5, 1);
        Escora escora = new Escora(5, 1);
        Plataforma plataforma = new Plataforma(5, 1);
        Roldana roldana = new Roldana(5);
        Pecas pecas = new Pecas(andaime, escora, plataforma, roldana);
        Contrato contrato = new Contrato(LocalDate.now(), LocalDate.now().plusDays(5L), FormaPagamento.PIX, endereco, contratante, pecas);
        contrato.setStatus("ATIVO");
        contratoRepository.save(contrato);
        contrato.setDataLocacao(LocalDate.now().minusDays(5));
        contrato.setDataDevolucao(LocalDate.now().minusDays(2));
        contrato.setStatus("VENCIDO");
        contratoRepository.save(contrato);
    }

    @Test
    public void listarContratosDoContratante() {
        String cpf = "42145065024";
        List<Contrato> listaContrato = contratoRepository.listarContratosDoContratante(cpf);
        assertThat(listaContrato.isEmpty()).isFalse();
    }

    @Test
    public void listarContratosDoEndereco() {
        String cpf = "42145065024";
        List<Contrato> listaPorContratante = contratoRepository.listarContratosDoContratante(cpf);
        Endereco endereco = listaPorContratante.get(0).getEndereco();
        List<Contrato> listaPorEndereco = contratoRepository.listarContratosDoEndereco(endereco.getId());
        assertThat(listaPorEndereco.isEmpty()).isFalse();
    }

    @Test
    public void listarContratosAtivos() {
        List<Contrato> listaContrato = contratoRepository.listarContratosAtivos();
        assertThat(listaContrato.isEmpty()).isFalse();
    }

    @Test
    public void listarContratosVencidos() {
        List<Contrato> listaContrato = contratoRepository.listarContratosVencidos();
        assertThat(listaContrato.isEmpty()).isFalse();
    }
}
