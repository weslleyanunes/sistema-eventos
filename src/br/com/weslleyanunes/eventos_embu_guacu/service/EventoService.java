package br.com.weslleyanunes.eventos_embu_guacu.service;

import br.com.weslleyanunes.eventos_embu_guacu.model.Evento;
import br.com.weslleyanunes.eventos_embu_guacu.model.Usuario;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class EventoService {
    private static final String ARQUIVO = "data/eventos.data";
    private final List<Evento> eventos;
    private final UsuarioService usuarioService;

    public EventoService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
        this.eventos = carregarEventos();
    }

    public void cadastrarEvento(String nome, String endereco, String categoria, LocalDateTime horario,
                                String descricao) {
        Evento evento = new Evento(nome, endereco, categoria, horario, descricao);
        eventos.add(evento);
        salvarEventos();
        System.out.println("Evento cadastrado com sucesso!");
    }

    public void listarEventosOrdenados() {
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento cadastrado.");
            return;
        }

        System.out.println("\n=== Lista de Eventos ===");
        eventos.stream().sorted(Comparator.comparing(Evento::getHorario))
                .forEach(e -> System.out.println((eventos.indexOf(e) + 1) + " - " + e));
    }

    public void participarEmEvento(Scanner scanner) {
        listarEventosOrdenados();
        if (eventos.isEmpty())
            return;

        System.out.print("Digite o número do evento: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice < 1 || indice > eventos.size()) {
            System.out.println("Evento inválido!");
            return;
        }

        Evento evento = eventos.get(indice - 1);

        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();
        Usuario usuario = usuarioService.buscarUsuarioPorCpf(cpf);

        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        evento.adicionarParticipante(usuario);
        salvarEventos();
        System.out.println("Participação confirmada em: " + evento.getNome());
    }

    public void cancelarParticipacao(Scanner scanner) {
        listarEventosOrdenados();
        if (eventos.isEmpty())
            return;

        System.out.print("Digite o número do evento: ");
        int indice = scanner.nextInt();
        scanner.nextLine();

        if (indice < 1 || indice > eventos.size()) {
            System.out.println("Evento inválido!");
            return;
        }

        Evento evento = eventos.get(indice - 1);

        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();
        Usuario usuario = usuarioService.buscarUsuarioPorCpf(cpf);

        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        evento.removerParticipante(usuario);
        salvarEventos();
        System.out.println("Participação cancelada em: " + evento.getNome());
    }

    public void listarEventosDoUsuario(Scanner scanner) {
        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();
        Usuario usuario = usuarioService.buscarUsuarioPorCpf(cpf);

        if (usuario == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.println("\n=== Meus Eventos ===");
        eventos.stream().filter(e -> e.getParticipantes().contains(usuario))
                .forEach(e -> System.out.println((eventos.indexOf(e) + 1) + " - " + e));
    }

    private void salvarEventos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(eventos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private List<Evento> carregarEventos() {
        File file = new File(ARQUIVO);
        if (!file.exists())
            return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Evento>) ois.readObject();
        } catch (Exception e) {
            System.out.println("Erro ao carregar eventos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
