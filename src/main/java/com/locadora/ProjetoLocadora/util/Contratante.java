package com.locadora.ProjetoLocadora.util;

import jakarta.persistence.*;

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

    @OneToMany(mappedBy = "contratante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contrato> contratos;

    public Contratante() {}

    public Contratante(String cpf, String nome, String telefone, List<Contrato> contratos) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.contratos = contratos;
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
