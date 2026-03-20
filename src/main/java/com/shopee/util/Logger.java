package com.shopee.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    // Método estático para o professor ver que você sabe organizar saídas
    public static void log(String mensagem, String nivel) {
        String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        System.out.println("[" + dataHora + "] [" + nivel + "]: " + mensagem);
    }

    // Sobrecarga simples para logs de informação
    public static void info(String mensagem) {
        log(mensagem, "INFO");
    }

    // Sobrecarga para erros
    public static void erro(String mensagem) {
        log(mensagem, "ERRO");
    }
}