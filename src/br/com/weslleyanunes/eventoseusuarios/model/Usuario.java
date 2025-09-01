package br.com.weslleyanunes.eventoseusuarios.model;

import java.io.Serializable;

public record Usuario(String nome, String cpf, String telefone, String email) implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return String.format("%s | CPF: %s | Tel: %s | Email: %s", nome, cpf, telefone, email);
    }
}
