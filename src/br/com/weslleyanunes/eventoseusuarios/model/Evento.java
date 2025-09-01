package br.com.weslleyanunes.eventoseusuarios.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String nome;
    private final String endereco;
    private final String categoria;
    private final LocalDateTime horario;
    private final String descricao;
    private final List<Usuario> participantes;

    public Evento(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        if (nome == null || endereco == null || categoria == null || horario == null || descricao == null) {
            throw new IllegalArgumentException("Todos os campos do evento são obrigatórios!");
        }
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
        this.participantes = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCategoria() {
        return categoria;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public void adicionarParticipante(Usuario usuario) {
        if (!participantes.contains(usuario)) {
            participantes.add(usuario);
        }
    }

    public void removerParticipante(Usuario usuario) {
        participantes.remove(usuario);
    }

    public String formatarHorario() {
        return horario.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %s", nome, endereco, categoria, formatarHorario(), descricao);
    }
}
