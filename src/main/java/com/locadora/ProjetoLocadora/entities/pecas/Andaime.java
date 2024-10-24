package com.locadora.ProjetoLocadora.entities.pecas;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Andaime implements ValorTotal{
    @Column(name = "qtd_andaime", nullable = false)
    private int qtdAndaime;
    @Column(name = "tam_andaime")
    private int tamanhoAndaime;
    @Column(name = "valor_andaime", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private double valorAndaime = 0;

    public Andaime() {}

    public Andaime(int qtdAndaime, int tamanhoAndaime) {
        this.qtdAndaime = qtdAndaime;
        this.tamanhoAndaime = tamanhoAndaime;
        valorTotal();
    }

    public int getQtdAndaime() {
        return qtdAndaime;
    }

    public void setQtdAndaime(int qtdAndaime) {
        this.qtdAndaime = qtdAndaime;
    }

    public int getTamanhoAndaime() {
        return tamanhoAndaime;
    }

    public void setTamanhoAndaime(int tamanhoAndaime) {
        this.tamanhoAndaime = tamanhoAndaime;
    }

    public double getValorAndaime() {
        return valorAndaime;
    }

    private void setValorAndaime(double valorAndaime) {
        this.valorAndaime = valorAndaime;
    }

    @Override
    public void valorTotal() {
        switch (getTamanhoAndaime()) {
            case 1:
                if (getQtdAndaime() <= 20) {
                    setValorAndaime(3.00 * getQtdAndaime());
                }
                if (getQtdAndaime() <= 50) {
                    setValorAndaime(2.70 * getQtdAndaime());
                } else {
                    setValorAndaime(2.50 * getQtdAndaime());
                }
            case 2:
                if (getQtdAndaime() <= 20) {
                    setValorAndaime(3.30 * getQtdAndaime());
                }
                if (getQtdAndaime() <= 50) {
                    setValorAndaime(3.00 * getQtdAndaime());
                } else {
                    setValorAndaime(2.70 * getQtdAndaime());
                }
            case 3:
                if (getQtdAndaime() <= 20) {
                    setValorAndaime(3.60 * getQtdAndaime());
                }
                if (getQtdAndaime() <= 50) {
                    setValorAndaime(3.30 * getQtdAndaime());
                } else {
                    setValorAndaime(3.00 * getQtdAndaime());
                }
        }
    }
}
