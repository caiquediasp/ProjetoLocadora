package com.locadora.ProjetoLocadora.util;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.locadora.ProjetoLocadora.generator.IdGenerator;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;

@Entity
@Table(name = "tb_contrato")
public class Contrato {
    @Id
    @GeneratedValue(generator = IdGenerator.generatorName)
    @GenericGenerator(name = IdGenerator.generatorName, strategy = "uuid")
    @Column(name = "id", unique = true, nullable = false)
    private String id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cpf_contratante", referencedColumnName = "cpf", nullable = false)
    private Contratante contratante;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco", referencedColumnName = "id", nullable = false)
    private Endereco endereco;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pecas", referencedColumnName = "id", nullable = false)
    private Pecas pecas;
    @Column(name = "data_locacao", nullable = false)
    private LocalDate dataLocacao;
    @Column(name = "data_devolucao", nullable = false)
    private LocalDate dataDevolucao;
    @Column(name = "forma_pagamento", nullable = false)
    @Enumerated(EnumType.STRING)
    private FormaPagamento formaPagamento;
    @Column(name = "valor_total", nullable = false)
    private double valorTotal;

    public Contrato() {}

    public Contrato(LocalDate dataLocacao, LocalDate dataDevolucao, FormaPagamento formaPagamento, Contratante contratante, Pecas pecas) {
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.formaPagamento = formaPagamento;
        this.contratante = contratante;
        this.pecas = pecas;
        this.valorTotal = pecas.valorTotal();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(LocalDate dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    @JsonBackReference
    public Contratante getContratante() {
        return contratante;
    }

    public void setContratante(Contratante contratante) {
        this.contratante = contratante;
    }

    public Pecas getPecas() {
        return pecas;
    }

    public void setPecas(Pecas pecas) {
        this.pecas = pecas;
    }
    @JsonBackReference
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal){
        this.valorTotal = valorTotal;
    }
}
