package com.locadora.ProjetoLocadora.util;

import com.locadora.ProjetoLocadora.util.pecas.Andaime;
import com.locadora.ProjetoLocadora.util.pecas.Escora;
import com.locadora.ProjetoLocadora.util.pecas.Plataforma;
import com.locadora.ProjetoLocadora.util.pecas.Roldana;
import jakarta.persistence.*;
@Entity
@Table(name = "tb_pecas")
public class Pecas{
    @Id
    @Column(name = "id")
    private String id;
    @OneToOne
    @MapsId
    private Contrato contrato;
    @Embedded
    private Andaime andaime;
    @Embedded
    private Escora escora;
    @Embedded
    private Plataforma plataforma;
    @Embedded
    private Roldana roldana;

    public Pecas() {}

    public Pecas(String id, Andaime andaime, Escora escora, Plataforma plataforma, Roldana roldana) {
        this.id = id;
        this.andaime = andaime;
        this.escora = escora;
        this.plataforma = plataforma;
        this.roldana = roldana;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Andaime getAndaime() {
        return andaime;
    }

    public void setAndaime(Andaime andaime) {
        this.andaime = andaime;
    }

    public Escora getEscora() {
        return escora;
    }

    public void setEscora(Escora escora) {
        this.escora = escora;
    }

    public Plataforma getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    public Roldana getRoldana() {
        return roldana;
    }

    public void setRoldana(Roldana roldana) {
        this.roldana = roldana;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public double valorTotal() {
        return andaime.getValorAndaime() + escora.getValorEscora() + plataforma.getValorPlataforma() + escora.getValorEscora();
    }
}
