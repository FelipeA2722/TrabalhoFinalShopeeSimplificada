package com.shopee.pattern.factory;

import com.shopee.pattern.strategy.*;

public class PagamentoFactory {
    public static PagamentoStrategy obterMetodo(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "cartao" -> new CartaoCreditoStrategy();
            case "boleto" -> new BoletoStrategy();
            case "pix" -> new PixStrategy();
            default -> throw new IllegalArgumentException("Método de pagamento não suportado: " + tipo);
        };
    }
}