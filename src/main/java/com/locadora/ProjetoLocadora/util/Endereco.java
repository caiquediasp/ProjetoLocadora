package com.locadora.ProjetoLocadora.util;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_endereco")
public class Endereco {
    @Id
    @Column(name = "id_contrato", unique = true, nullable = false)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id", unique = true)
    private Contrato contrato;
    @Column(name = "cep", nullable = false)
    private String cep;
    @Column(name = "bairro", nullable = false)
    private String bairro;
    @Column(name = "rua", nullable = false)
    private String rua;
    @Column(name = "numero", nullable = false)
    private int numero;

    public Endereco() {}

    public Endereco(Long id, Contrato contrato, String cep, String bairro, String rua, int numero) {
        this.id = id;
        this.contrato = contrato;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
