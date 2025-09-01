package br.com.weslleyanunes.eventoseusuarios.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String nome;
    private final String cpf;
    private final String telefone;
    private final String email;

    public Usuario(String nome, String cpf, String telefone, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("%s | CPF: %s | Tel: %s | Email: %s", nome, cpf, telefone, email);
    }
}
