package com.locadora.ProjetoLocadora.entities.pecas;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Roldana implements ValorTotal{
    @Column(name = "qtd_roldana", nullable = false)
    private int qtdRoldana;
    @Column(name = "valor_roldana", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double valorRoldana;

    public Roldana() {}

    public Roldana(int qtdRoldana) {
        this.qtdRoldana = qtdRoldana;
        valorTotal();
    }

    public int getQtdRoldana() {
        return qtdRoldana;
    }

    public void setQtdRoldana(int qtdRoldana) {
        this.qtdRoldana = qtdRoldana;
    }

    public double getValorRoldana() {
        return valorRoldana;
    }

    public void setValorRoldana(double valorRoldana) {
        this.valorRoldana = valorRoldana;
    }

    @Override
    public void valorTotal() {
        if(getQtdRoldana() <= 15){
            setValorRoldana(3.25 * getQtdRoldana());
        }
        if(getQtdRoldana() <= 30){
            setValorRoldana(2.80 * getQtdRoldana());
        } else{
            setValorRoldana(2.50 * getQtdRoldana());
        }
    }
}
