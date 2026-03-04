package org.example;

public class Produto {
    String nome;
    int totalVendido = 0;
    double faturamento = 0.0;

    public Produto(String nome){
        this.nome = nome;
    }
    public void registrarVenda( int quantidade, double precoUnitario){
        this.totalVendido += quantidade;
        this.faturamento += quantidade * precoUnitario;
    }

}
