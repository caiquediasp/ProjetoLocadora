package com.locadora.ProjetoLocadora.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.locadora.ProjetoLocadora.generator.IdGenerator;
import com.locadora.ProjetoLocadora.entities.pecas.Andaime;
import com.locadora.ProjetoLocadora.entities.pecas.Escora;
import com.locadora.ProjetoLocadora.entities.pecas.Plataforma;
import com.locadora.ProjetoLocadora.entities.pecas.Roldana;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pecas")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Pecas{
    @Id
    @GeneratedValue(generator = IdGenerator.generatorName)
    @GenericGenerator(name = IdGenerator.generatorName, strategy = "uuid")
    @Column(name = "id")
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
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

    @JsonIgnore
    public List<Contrato> getContrato() {
        return contratos;
    }

    public void setContrato(List<Contrato> contratos) {
        this.contratos = contratos;
    }

    public double valorTotal() {
        andaime.valorTotal();
        escora.valorTotal();
        plataforma.valorTotal();
        roldana.valorTotal();
        return andaime.getValorAndaime() + escora.getValorEscora() + plataforma.getValorPlataforma() + roldana.getValorRoldana();
    }
}
