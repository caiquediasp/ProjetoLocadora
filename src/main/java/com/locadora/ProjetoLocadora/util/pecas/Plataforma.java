package com.locadora.ProjetoLocadora.util.pecas;

public class Plataforma implements ValorTotal{
    private int qtdPlataforma;
    private int tamanhoPlataforma;
    private double valorPlataforma;

    public Plataforma() {}

    public Plataforma(int qtdPlataforma, int tamanhoPlataforma) {
        this.qtdPlataforma = qtdPlataforma;
        this.tamanhoPlataforma = tamanhoPlataforma;
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
    public double valorTotal() {
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

        return getValorPlataforma();
    }
}
