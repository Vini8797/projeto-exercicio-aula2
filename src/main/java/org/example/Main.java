package org.example;

public class Main {
    static void main(String[] args) {
        String caminho = args.length > 0 ? args[0] : "vendas.csv";
        SistemaVendas sistema = new SistemaVendas();
        sistema.carregar(caminho);
        sistema.imprimirRelatorio();
    }
}