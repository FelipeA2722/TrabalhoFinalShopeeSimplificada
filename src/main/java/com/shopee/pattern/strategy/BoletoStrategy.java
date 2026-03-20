package com.shopee.pattern.strategy;

public class BoletoStrategy implements PagamentoStrategy {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("--- Gerando Boleto ---");
        System.out.println("Código de barras: 23793.38128 60080.031350 28000.633042");
        System.out.println("Vencimento: em 3 dias.");
    }
}

