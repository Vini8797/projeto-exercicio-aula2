package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class SistemaVendas {

    ArrayList<Venda> vendas = new ArrayList<>();
    HashMap<String, Cliente> clientes = new HashMap<>();
    HashMap<String, Produto> produtos = new HashMap<>();

    double faturamentoTotal = 0.0;
    int totalItens = 0;

    public void carregar(String caminho) {
        vendas.clear();
        clientes.clear();
        produtos.clear();
        faturamentoTotal = 0.0;
        totalItens = 0;

        vendas = Arquivo.abrirArquivo(caminho);

        for (Venda v : vendas) {
            double totalVenda = v.quantidade * v.precoUnitario;
            faturamentoTotal += totalVenda;
            totalItens += v.quantidade;

            if (!clientes.containsKey(v.cliente)) {
                clientes.put(v.cliente, new Cliente(v.cliente));
            }
            clientes.get(v.cliente).adicionarCompra(totalVenda);

            if (!produtos.containsKey(v.produto)) {
                produtos.put(v.produto, new Produto(v.produto));
            }
            produtos.get(v.produto).registrarVenda(v.quantidade, v.precoUnitario);
        }
    }

    public double getFaturamentoTotal() {
        return faturamentoTotal;
    }

    public int getTotalItens() {
        return totalItens;
    }

    public double getMediaPorVenda() {
        return faturamentoTotal / vendas.size();
    }

    public Cliente getClienteQueMaisGastou() {
        Cliente top = null;
        for (Cliente c : clientes.values()) {
            if (top == null || c.totalGasto > top.totalGasto) {
                top = c;
            }
        }return top;
    }

    public Produto getProdutoMaisVendido() {
        Produto top = null;
        for (Produto p : produtos.values()) {
            if (top == null || p.totalVendido > top.totalVendido) {
                top = p;
            }
        }return top;
    }

    public Produto getProdutoMaiorFaturamento() {
        Produto top = null;
        for (Produto p : produtos.values()) {
            if (top == null || p.faturamento > top.faturamento) {
                top = p;
            }
        }return top;
    }

    public void imprimirRelatorio() {
        System.out.println("===== RELATÓRIO =====");
        System.out.printf("Vendas lidas: %d%n", vendas.size());
        System.out.printf("Faturamento total: R$ %.2f%n", getFaturamentoTotal());
        System.out.printf("Total de itens vendidos: %d%n", getTotalItens());
        System.out.printf("Valor médio por venda: R$ %.2f%n", getMediaPorVenda());
        System.out.println();

        Cliente cTop = getClienteQueMaisGastou();
        if (cTop != null) {
            System.out.printf("Cliente que mais gastou: %s (R$ %.2f) - %d compras%n",
                    cTop.nome, cTop.totalGasto, cTop.quantidadeCompras);
        } else {
            System.out.println("Cliente que mais gastou: -");
        }

        System.out.println();
        System.out.println("Total gasto por cada cliente e quantidade de compras:");
        for (Cliente c : clientes.values()) {
            System.out.printf(" - %s: R$ %.2f | Compras: %d%n", c.nome, c.totalGasto, c.quantidadeCompras);
        }

        System.out.println();
        Produto pTop = getProdutoMaisVendido();
        if (pTop != null) {
            System.out.printf("Produto mais vendido (quantidade): %s (%d unidades)%n",
                    pTop.nome, pTop.totalVendido);
        }

        Produto pFat = getProdutoMaiorFaturamento();
        if (pFat != null) {
            System.out.printf("Produto com maior faturamento: %s (R$ %.2f)%n",
                    pFat.nome, pFat.faturamento);
        }

        System.out.println();
        System.out.println("Quantidade vendida e faturamento por produto:");
        for (Produto p : produtos.values()) {
            System.out.printf(" - %s: %d unidades | Faturamento: R$ %.2f%n",
                    p.nome, p.totalVendido, p.faturamento);
        }
    }
}