package com.locadora.ProjetoLocadora.util.pecas;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Plataforma implements ValorTotal{
    @Column(name = "qtd_plataforma", nullable = false)
    private int qtdPlataforma;
    @Column(name = "tam_plataforma")
    private int tamanhoPlataforma;
    @Column(name = "valor_plataforma", nullable = false)
    private double valorPlataforma;

    public Plataforma() {}

    public Plataforma(int qtdPlataforma, int tamanhoPlataforma) {
        this.qtdPlataforma = qtdPlataforma;
        this.tamanhoPlataforma = tamanhoPlataforma;
        valorTotal();
    }

    public int getQtdPlataforma() {
        return qtdPlataforma;
    }

    public void setQtdPlataforma(int qtdPlataforma) {
        this.qtdPlataforma = qtdPlataforma;
    }

    public int getTamanhoPlataforma() {
        return tamanhoPlataforma;
    }

    public void setTamanhoPlataforma(int tamanhoPlataforma) {
        this.tamanhoPlataforma = tamanhoPlataforma;
    }

    public double getValorPlataforma() {
        return valorPlataforma;
    }

    public void setValorPlataforma(double valorPlataforma) {
        this.valorPlataforma = valorPlataforma;
    }

    @Override
    public void valorTotal() {
        switch (getTamanhoPlataforma()) {
            case 1:
                if (getQtdPlataforma() <= 15) {
                    setValorPlataforma(4.35 * getQtdPlataforma());
                }
                if (getQtdPlataforma() <= 30) {
                    setValorPlataforma(4.00 * getQtdPlataforma());
                } else {
                    setValorPlataforma(3.75 * getQtdPlataforma());
                }
            case 2:
                if (getQtdPlataforma() <= 15) {
                    setValorPlataforma(4.70 * getQtdPlataforma());
                }
                if (getQtdPlataforma() <= 30) {
                    setValorPlataforma(4.35 * getQtdPlataforma());
                } else {
                    setValorPlataforma(4.00 * getQtdPlataforma());
                }
            case 3:
                if (getQtdPlataforma() <= 15) {
                    setValorPlataforma(5.00 * getQtdPlataforma());
                }
                if (getQtdPlataforma() <= 30) {
                    setValorPlataforma(4.70 * getQtdPlataforma());
                } else {
                    setValorPlataforma(4.35 * getQtdPlataforma());
                }
        }
    }
}
