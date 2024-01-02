package com.locadora.ProjetoLocadora.util;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_contratante")
public class Contratante {
    @Id
    private String cpf;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "telefone", nullable = false)
    private String telefone;

    @OneToMany(mappedBy = "contratante", orphanRemoval = true)
    private List<Contrato> contratos = new ArrayList<>();

    public Contratante() {}

    public Contratante(String cpf, String nome, String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(List<Contrato> contratos) {
        this.contratos = contratos;
    }
}
