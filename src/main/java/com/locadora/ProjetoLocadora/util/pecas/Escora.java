package com.locadora.ProjetoLocadora.util.pecas;

public class Escora implements ValorTotal{
    private int qtdEscora;
    private int tamanhoEscora;
    private double valorEscora;

    public Escora() {}

    public Escora(int qtdEscora, int tamanhoEscora) {
        this.qtdEscora = qtdEscora;
        this.tamanhoEscora = tamanhoEscora;
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
    public double valorTotal() {
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

        return getValorEscora();
    }
}
