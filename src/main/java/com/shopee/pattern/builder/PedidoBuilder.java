package com.shopee.pattern.builder;

import com.shopee.model.Pedido;

public class PedidoBuilder {
    private Pedido pedido;

    public PedidoBuilder() {
        this.pedido = new Pedido();
    }

    public PedidoBuilder paraCliente(int clienteId) {
        this.pedido.setClienteId(clienteId);
        return this;
    }

    public PedidoBuilder comValor(double valor) {
        this.pedido.setTotal(valor);
        return this;
    }

    public PedidoBuilder comStatus(String status) {
        this.pedido.setStatus(status);
        return this;
    }

    public Pedido build() {
        return this.pedido;
    }
}