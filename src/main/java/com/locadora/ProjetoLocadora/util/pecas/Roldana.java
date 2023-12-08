package com.locadora.ProjetoLocadora.util.pecas;

public class Roldana implements ValorTotal{
    private int qtdRoldana;
    private double valorRoldana;

    public Roldana() {}

    public Roldana(int qtdRoldana) {
        this.qtdRoldana = qtdRoldana;
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
    public double valorTotal() {
        if(getQtdRoldana() <= 15){
            setValorRoldana(3.25 * getQtdRoldana());
        }
        if(getQtdRoldana() <= 30){
            setValorRoldana(2.80 * getQtdRoldana());
        } else{
            setValorRoldana(2.50 * getQtdRoldana());
        }

        return getValorRoldana();
    }
}
