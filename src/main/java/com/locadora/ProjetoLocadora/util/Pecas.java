package com.locadora.ProjetoLocadora.util;

import com.locadora.ProjetoLocadora.generator.IdGenerator;
import com.locadora.ProjetoLocadora.util.pecas.Andaime;
import com.locadora.ProjetoLocadora.util.pecas.Escora;
import com.locadora.ProjetoLocadora.util.pecas.Plataforma;
import com.locadora.ProjetoLocadora.util.pecas.Roldana;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_pecas")
public class Pecas{
    @Id
    @GeneratedValue(generator = IdGenerator.generatorName)
    @GenericGenerator(name = IdGenerator.generatorName, strategy = "uuid")
    @Column(name = "id")
    private String id;
    @OneToMany(mappedBy = "pecas")
    private List<Contrato> contratos = new ArrayList<>();
    @Embedded
    private Andaime andaime;
    @Embedded
    private Escora escora;
    @Embedded
    private Plataforma plataforma;
    @Embedded
    private Roldana roldana;

    public Pecas() {}

    public Pecas(Andaime andaime, Escora escora, Plataforma plataforma, Roldana roldana) {
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

    public List<Contrato> getContrato() {
        return contratos;
    }

    public void setContrato(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public double valorTotal() {
        return andaime.getValorAndaime() + escora.getValorEscora() + plataforma.getValorPlataforma() + escora.getValorEscora();
    }
}
