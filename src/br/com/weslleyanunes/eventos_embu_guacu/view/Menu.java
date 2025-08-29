package br.com.weslleyanunes.eventos_embu_guacu.view;

import java.util.Scanner;

public class Menu {
    public static void exibirMenuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("\n1 - Cadastrar Usuário");
            System.out.println("2 - Cadastrar Evento");
            System.out.println("3 - Listar Eventos");
            System.out.println("4 - Confirmar presença em evento");
            System.out.println("5 - Cancelar presença em evento");
            System.out.println("0 - Sair");
            System.out.print("\nEscolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1: System.out.println(">>> Cadastrar usuário <<<"); break;
                case 2: System.out.println(">>> Cadastrar evento <<<"); break;
                case 3: System.out.println(">>> Listar eventos <<<"); break;
                case 4: System.out.println(">>> Confirmar presença <<<"); break;
                case 5: System.out.println(">>> Cancelar presença <<<"); break;
                case 0: System.out.println("Encerrando sistema."); break;
                default: System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        sc.close();
    }
}
