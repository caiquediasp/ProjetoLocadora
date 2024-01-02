package com.locadora.ProjetoLocadora.util.pecas;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Escora implements ValorTotal{
    @Column(name = "qtd_escora", nullable = false)
    private int qtdEscora;
    @Column(name = "tam_escora")
    private int tamanhoEscora;
    @Column(name = "valor_escora", nullable = false)
    private double valorEscora = 0;

    public Escora() {}

    public Escora(int qtdEscora, int tamanhoEscora) {
        this.qtdEscora = qtdEscora;
        this.tamanhoEscora = tamanhoEscora;
        valorTotal();
    }

    public int getQtdEscora() {
        return qtdEscora;
    }

    public void setQtdEscora(int qtdEscora) {
        this.qtdEscora = qtdEscora;
    }

    public int getTamanhoEscora() {
        return tamanhoEscora;
    }

    public void setTamanhoEscora(int tamanhoEscora) {
        this.tamanhoEscora = tamanhoEscora;
    }

    public double getValorEscora() {
        return valorEscora;
    }

    public void setValorEscora(double valorEscora) {
        this.valorEscora = valorEscora;
    }

    @Override
    public void valorTotal() {
        switch (getTamanhoEscora()) {
            case 1:
                if (getQtdEscora() <= 15) {
                    setValorEscora(5.00 * getQtdEscora());
                }
                if (getQtdEscora() <= 35) {
                    setValorEscora(4.50 * getQtdEscora());
                } else {
                    setValorEscora(4.20 * getQtdEscora());
                }
            case 2:
                if (getQtdEscora() <= 15) {
                    setValorEscora(5.40 * getQtdEscora());
                }
                if (getQtdEscora() <= 35) {
                    setValorEscora(5.00 * getQtdEscora());
                } else {
                    setValorEscora(4.60 * getQtdEscora());
                }
            case 3:
                if (getQtdEscora() <= 15) {
                    setValorEscora(5.80 * getQtdEscora());
                }
                if (getQtdEscora() <= 35) {
                    setValorEscora(5.40 * getQtdEscora());
                } else {
                    setValorEscora(5.00 * getQtdEscora());
                }
        }
    }
}
