package br.com.weslleyanunes.eventoseusuarios.view;

import br.com.weslleyanunes.eventoseusuarios.model.Evento;
import br.com.weslleyanunes.eventoseusuarios.model.Usuario;
import br.com.weslleyanunes.eventoseusuarios.service.EventoService;
import br.com.weslleyanunes.eventoseusuarios.service.UsuarioService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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

        while (true) {
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
            System.out.println("8 - Excluir Evento"); // Nova opção
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
                    while (true) {
                        System.out.println("\n1 - Corrigir Usuário");
                        System.out.println("2 - Voltar ao Menu Principal");
                        System.out.println("3 - Sair");
                        int subOpcao = scanner.nextInt();
                        scanner.nextLine(); // limpar buffer
                        if (subOpcao == 1) {
                            System.out.print("Digite o CPF do usuário a corrigir: ");
                            String cpf = scanner.nextLine();
                            Usuario usuario = usuarioService.buscarUsuarioPorCpf(cpf);
                            if (usuario != null) {
                                System.out.print("Novo nome: ");
                                String novoNome = scanner.nextLine();
                                System.out.print("Novo telefone: ");
                                String novoTelefone = scanner.nextLine();
                                System.out.print("Novo email: ");
                                String novoEmail = scanner.nextLine();
                                usuarioService.atualizarUsuario(cpf, novoNome, novoTelefone, novoEmail);
                            } else {
                                System.out.println("Usuário não encontrado.");
                            }
                        } else if (subOpcao == 2) {
                            break;
                        } else if (subOpcao == 3) {
                            System.out.println("Saindo...");
                            System.exit(0);
                        }
                    }
                    break;
                case 3:
                    cadastrarEvento();
                case 4:
                    eventoService.listarEventosOrdenados();
                    while (true) {
                        System.out.println("\n1 - Corrigir Evento");
                        System.out.println("2 - Voltar ao Menu Principal");
                        System.out.println("3 - Sair");
                        int subOpcao = scanner.nextInt();
                        scanner.nextLine(); // limpar buffer
                        if (subOpcao == 1) {
                            List<Evento> eventos = eventoService.getEventos();
                            if (eventos.isEmpty()) {
                                System.out.println("Nenhum evento cadastrado.");
                                continue;
                            }
                            System.out.print("Selecione o número do evento a corrigir: ");
                            int numero = scanner.nextInt() - 1;
                            scanner.nextLine(); // limpar buffer
                            if (numero >= 0 && numero < eventos.size()) {
                                Evento evento = eventos.get(numero);
                                System.out.print("Novo endereço: ");
                                String novoEndereco = scanner.nextLine();
                                System.out.print("Nova data e hora (dd/MM/yyyy HH:mm): ");
                                String dataHoraStr = scanner.nextLine();
                                LocalDateTime novoHorario;
                                try {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                                    novoHorario = LocalDateTime.parse(dataHoraStr, formatter);
                                } catch (Exception e) {
                                    System.out.println("Data inválida!");
                                    continue;
                                }
                                System.out.print("Nova descrição: ");
                                String novaDescricao = scanner.nextLine();
                                System.out.print("Nova categoria: ");
                                String novaCategoria = scanner.nextLine();
                                eventoService.atualizarEvento(evento.getNome(), novoEndereco, novaCategoria, novoHorario, novaDescricao);
                            } else {
                                System.out.println("Número inválido.");
                            }
                        } else if (subOpcao == 2) {
                            break;
                        } else if (subOpcao == 3) {
                            System.out.println("Saindo...");
                            System.exit(0);
                        }
                    }
                    break;
                case 5:
                    eventoService.participarEmEvento(scanner);
                    break;
                case 6:
                    eventoService.cancelarParticipacao(scanner);
                    break;
                case 7:
                    System.out.print("Digite seu CPF: ");
                    String cpfConsulta = scanner.nextLine();
                    eventoService.listarEventosDoUsuario(cpfConsulta);
                    while (true) {
                        System.out.println("\n1 - Corrigir Evento");
                        System.out.println("2 - Voltar ao Menu Principal");
                        System.out.println("3 - Sair");
                        int subOpcao = scanner.nextInt();
                        scanner.nextLine(); // limpar buffer
                        if (subOpcao == 1) {
                            System.out.print("Digite seu CPF: ");
                            String cpf = scanner.nextLine();
                            List<Evento> eventosDoUsuario = eventoService.listarEventosDoUsuario(cpf);
                            if (eventosDoUsuario.isEmpty()) {
                                System.out.println("Você não participa de nenhum evento.");
                                continue;
                            }
                            System.out.print("Selecione o número do evento a corrigir: ");
                            int numero = scanner.nextInt() - 1;
                            scanner.nextLine(); // limpar buffer
                            if (numero >= 0 && numero < eventosDoUsuario.size()) {
                                Evento evento = eventosDoUsuario.get(numero);
                                System.out.print("Novo endereço: ");
                                String novoEndereco = scanner.nextLine();
                                System.out.print("Nova categoria: ");
                                String novaCategoria = scanner.nextLine();
                                System.out.print("Nova data e hora (dd/MM/yyyy HH:mm): ");
                                String dataHoraStr = scanner.nextLine();
                                LocalDateTime novoHorario;
                                try {
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                                    novoHorario = LocalDateTime.parse(dataHoraStr, formatter);
                                } catch (Exception e) {
                                    System.out.println("Data inválida!");
                                    continue;
                                }
                                System.out.print("Nova descrição: ");
                                String novaDescricao = scanner.nextLine();

                                eventoService.atualizarEvento(evento.getNome(), novoEndereco, novaCategoria, novoHorario, novaDescricao);
                                System.out.println("Evento atualizado com sucesso!");
                            } else {
                                System.out.println("Número inválido.");
                            }
                        } else if (subOpcao == 2) {
                            break;
                        } else if (subOpcao == 3) {
                            System.out.println("Saindo...");
                            System.exit(0);
                        }
                    }
                    break;
                case 8:
                    eventoService.listarEventosOrdenados(); // Exibe a lista ordenada
                    if (!eventoService.getEventos().isEmpty()) {
                        System.out.print("Digite o número do evento a excluir: ");
                        int numeroExcluir = scanner.nextInt();
                        int indiceExcluir = numeroExcluir - 1;
                        eventoService.removerEventoPorOrdenacao(indiceExcluir);
                    } else {
                        System.out.println("Nenhum evento cadastrado para excluir.");
                    }

                    // ← Adicionado: Sub-menu com opções de navegação
                    while (true) {
                        System.out.println("\n1 - Excluir outro evento");
                        System.out.println("2 - Voltar ao Início");
                        System.out.println("3 - Sair");
                        System.out.print("Escolha uma opção: ");
                        int subOpcao = scanner.nextInt();
                        scanner.nextLine(); // Limpa buffer

                        if (subOpcao == 1) {
                            eventoService.listarEventosOrdenados();
                            if (!eventoService.getEventos().isEmpty()) {
                                System.out.print("Digite o número do evento a excluir: ");
                                int numeroExcluir = scanner.nextInt();
                                int indiceExcluir = numeroExcluir - 1;
                                eventoService.removerEventoPorOrdenacao(indiceExcluir);
                            } else {
                                System.out.println("Nenhum evento cadastrado.");
                            }
                        } else if (subOpcao == 2) {
                            break; // Volta ao menu principal
                        } else if (subOpcao == 3) {
                            System.out.println("Saindo...");
                            System.exit(0);
                        } else {
                            System.out.println("Opção inválida!");
                        }
                    }
                    break;
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

        System.out.print("Digite a categoria: ");
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
