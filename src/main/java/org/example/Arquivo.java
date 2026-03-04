package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Arquivo {

    public static ArrayList<Venda> abrirArquivo(String nomeArquivo) {
        ArrayList<Venda> vendas = new ArrayList<>();
        try {BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
            String linha;

            br.readLine();

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(",");

                Venda venda = new Venda();
                venda.registraVenda(dados);
                vendas.add(venda);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao ler arquivo.");
        }
        return vendas;

    }

};