package br.com.weslleyanunes.eventoseusuarios.view;

import br.com.weslleyanunes.eventoseusuarios.service.EventoService;
import br.com.weslleyanunes.eventoseusuarios.service.UsuarioService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final UsuarioService usuarioService;
    private final EventoService eventoService;

    public Menu() {
        scanner = new Scanner(System.in);
        usuarioService = new UsuarioService();
        eventoService = new EventoService(usuarioService);
    }

    public void exibirMenuPrincipal() {
        int opcao = -1;

        while (opcao != 0) {
            limparConsole();
            System.out.println("====================================");
            System.out.println("  SISTEMA DE EVENTOS DE EMBU-GUAÇU   ");
            System.out.println("====================================");
            System.out.println("\n1 - Cadastrar Usuário");
            System.out.println("2 - Listar Usuários");
            System.out.println("3 - Cadastrar Evento");
            System.out.println("4 - Listar Eventos");
            System.out.println("5 - Participar de Evento");
            System.out.println("6 - Cancelar Participação");
            System.out.println("7 - Meus Eventos");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            limparConsole();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                case 2:
                    usuarioService.listarUsuarios();
                case 3:
                    cadastrarEvento();
                case 4:
                    eventoService.listarEventosOrdenados();
                case 5:
                    eventoService.participarEmEvento(scanner);
                case 6:
                    eventoService.cancelarParticipacao(scanner);
                case 7:
                    eventoService.listarEventosDoUsuario(scanner);
                case 0:
                    System.out.println("\nSaindo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

            if (opcao != 0) {
                System.out.println("\nPressione ENTER para voltar ao menu...");
                scanner.nextLine();
            }
        }
    }

    private void cadastrarUsuario() {
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Digite o telefone: ");
        String telefone = scanner.nextLine();

        System.out.print("Digite o email: ");
        String email = scanner.nextLine();

        usuarioService.cadastrarUsuario(nome, cpf, telefone, email);
    }

    private void cadastrarEvento() {
        System.out.print("Digite o nome do evento: ");
        String nome = scanner.nextLine();

        System.out.print("Digite o endereço: ");
        String endereco = scanner.nextLine();

        System.out.print("Digite a categoria (Festa, Show, Esporte...): ");
        String categoria = scanner.nextLine();

        System.out.print("Digite a data e hora (dd/MM/yyyy HH:mm): ");
        String dataHoraStr = scanner.nextLine();

        LocalDateTime horario;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            horario = LocalDateTime.parse(dataHoraStr, formatter);
        } catch (Exception e) {
            System.out.println("Data inválida! Cadastro cancelado.");
            return;
        }

        System.out.print("Digite a descrição: ");
        String descricao = scanner.nextLine();

        eventoService.cadastrarEvento(nome, endereco, categoria, horario, descricao);
    }

    private void limparConsole() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }
}
