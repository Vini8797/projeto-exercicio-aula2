package org.example;
import java.time.LocalDate;

public class Venda {

    int codigoVenda;
    String cliente;
    String produto;
    int quantidade;
    double precoUnitario;
    LocalDate data;

    public void registraVenda(String[] dados) {

        codigoVenda = Integer.parseInt(dados[0]);
        cliente = dados[1];
        produto = dados[2];
        quantidade = Integer.parseInt(dados[3]);
        precoUnitario = Double.parseDouble(dados[4]);
        data = LocalDate.parse(dados[5]);
    }

    public void getTotal(int quantidade,double precoUnitario){
        double total = quantidade * precoUnitario;
    }
}

