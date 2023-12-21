package com.locadora.ProjetoLocadora.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document("contratos")
public class Contrato {
    @Id
    private String id;
    private LocalDate dataLocacao;
    private LocalDate dataDevolucao;
    private FormaPagamento formaPagamento;
    private Contratante contratante;
    private Pecas pecas;
    private Endereco endereco;
    private double valorTotal;

    public Contrato() {}

    public Contrato(String id, LocalDate dataLocacao, LocalDate dataDevolucao, FormaPagamento formaPagamento, Contratante contratante, Pecas pecas, Endereco endereco) {
        this.id = id;
        this.dataLocacao = dataLocacao;
        this.dataDevolucao = dataDevolucao;
        this.formaPagamento = formaPagamento;
        this.contratante = contratante;
        this.pecas = pecas;
        this.endereco = endereco;
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
