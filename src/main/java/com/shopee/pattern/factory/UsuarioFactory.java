package com.shopee.pattern.factory;

import com.shopee.model.*;

public class UsuarioFactory {
    public static Usuario criar(String tipo) {
        if (tipo.equalsIgnoreCase("cliente")) {
            return new Cliente();
        } else if (tipo.equalsIgnoreCase("vendedor")) {
            return new Vendedor();
        }
        throw new IllegalArgumentException("Tipo de usuário desconhecido: " + tipo);
    }
}
