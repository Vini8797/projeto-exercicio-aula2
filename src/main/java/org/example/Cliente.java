package org.example;

public class Cliente {

    String nome;
    double totalGasto = 0.0;
    int quantidadeCompras = 0;

    public Cliente(String nome){
        this.nome = nome;
    }

    public void adicionarCompra(double valor){
        this.totalGasto += valor;
        this.quantidadeCompras++;
    }

}
