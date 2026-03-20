package com.shopee.pattern.strategy;

public class CartaoCreditoStrategy implements PagamentoStrategy {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("--- Cartão de Crédito ---");
        System.out.println("Conectando com a operadora...");
        System.out.println("Pagamento de R$ " + valor + " autorizado!");
    }
}