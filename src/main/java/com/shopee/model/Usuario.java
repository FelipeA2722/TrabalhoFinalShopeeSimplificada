package com.shopee.model;

import java.time.LocalDateTime;

public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String tipo;
    private LocalDateTime dataCadrastro;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getDataCadrastro() {
        return dataCadrastro;
    }

    public void setDataCadrastro(LocalDateTime dataCadrastro) {
        this.dataCadrastro = dataCadrastro;
    }

}
