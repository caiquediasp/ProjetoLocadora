package com.locadora.ProjetoLocadora.util;

import com.locadora.ProjetoLocadora.util.pecas.Andaime;
import com.locadora.ProjetoLocadora.util.pecas.Escora;
import com.locadora.ProjetoLocadora.util.pecas.Plataforma;
import com.locadora.ProjetoLocadora.util.pecas.Roldana;

public class Pecas {
    private Andaime andaime;
    private Escora escora;
    private Plataforma plataforma;
    private Roldana roldana;

    public Pecas() {}

    public Pecas(Andaime andaime, Escora escora, Plataforma plataforma, Roldana roldana) {
        this.andaime = andaime;
        this.escora = escora;
        this.plataforma = plataforma;
        this.roldana = roldana;
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

    public double valorTotal() {
        return andaime.getValorAndaime() + escora.getValorEscora() + plataforma.getValorPlataforma() + escora.getValorEscora();
    }
}
