package com.locadora.ProjetoLocadora.repository;

import com.locadora.ProjetoLocadora.util.*;
import com.locadora.ProjetoLocadora.util.pecas.Andaime;
import com.locadora.ProjetoLocadora.util.pecas.Escora;
import com.locadora.ProjetoLocadora.util.pecas.Plataforma;
import com.locadora.ProjetoLocadora.util.pecas.Roldana;
import org.apache.commons.lang3.BooleanUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ContratanteRepositoryTest {

    @Autowired
    ContratanteRepository contratanteRepository;
    @Autowired
    ContratoRepository contratoRepository;

    @BeforeEach
    void setup (){
        Contratante contratante = new Contratante("05433755363", "teste", "(xx)xxxxx-xxxx");
        Endereco endereco = new Endereco("12345-678", "teste", "teste", 1234);
        Andaime andaime = new Andaime(5, 1);
        Escora escora = new Escora(5, 1);
        Plataforma plataforma = new Plataforma(5, 1);
        Roldana roldana = new Roldana(5);
        Pecas pecas = new Pecas(andaime, escora, plataforma, roldana);
        Contrato contrato = new Contrato(LocalDate.now(), LocalDate.now().plusDays(5L), FormaPagamento.PIX, endereco, contratante, pecas);
        contrato.setStatus("ATIVO");
        contratanteRepository.save(contratante);
        contratoRepository.save(contrato);
    }

    @Test
    void quantidadeContratoDoContratante() {
        String cpf = "05433755363";
        int quantidadeContrato = contratanteRepository.quantidadeContratoDoContratante(cpf);
        boolean teste = quantidadeContrato > 0;
        assertThat(teste).isTrue();
    }

    @Test
    void quantidadeContratoDoContratanteNulo() {
        String cpf = "12334556742";
        int quantidadeContrato = contratanteRepository.quantidadeContratoDoContratante(cpf);
        boolean teste = quantidadeContrato > 0;
        assertThat(teste).isFalse();
    }

    @Test
    void contratanteExiste() {
        String cpf = "05433755363";
        Optional<Contratante> teste = contratanteRepository.findById(cpf);
        assertThat(teste.isPresent()).isTrue();
    }

    @Test
    void contratanteNaoExiste() {
        String cpf = "123.456.789-01";
        Optional<Contratante> teste = contratanteRepository.findById(cpf);
        assertThat(teste.isPresent()).isFalse();
    }
}