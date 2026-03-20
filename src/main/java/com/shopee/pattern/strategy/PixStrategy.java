package com.shopee.pattern.strategy;

// pattern/strategy/PixStrategy.java
public class PixStrategy implements PagamentoStrategy {
    @Override
    public void processarPagamento(double valor) {
        System.out.println("--- Processando PIX ---");
        System.out.println("Valor: R$ " + valor);
        System.out.println("Chave Aleatória: " + java.util.UUID.randomUUID());
        System.out.println("Pagamento PIX realizado com sucesso!");
    }
}

