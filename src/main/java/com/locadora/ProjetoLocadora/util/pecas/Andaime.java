package com.locadora.ProjetoLocadora.util.pecas;

public class Andaime implements ValorTotal{
    private int qtdAndaime;
    private int tamanhoAndaime;
    private double valorAndaime;

    public Andaime() {}

    public Andaime(int qtdAndaime, int tamanhoAndaime) {
        this.qtdAndaime = qtdAndaime;
        this.tamanhoAndaime = tamanhoAndaime;
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
    public double valorTotal() {
        double valor = 0;

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

        return getValorAndaime();
    }
}
