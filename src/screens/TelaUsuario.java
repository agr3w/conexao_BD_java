package screens;

import java.util.Scanner;
import entity.Usuario;

//** Classe com métodos estáticos para exibir telas e capturar dados do usuário
public class TelaUsuario {

    private static Scanner scanner = new Scanner(System.in);

    // ** Exibe o menu principal e retorna a opção selecionada
    public static int exibirMenu() {
        System.out.println("\n=== Sistema de Cadastro de Usuários ===");
        System.out.println("1 - Cadastrar usuário");
        System.out.println("2 - Editar usuário");
        System.out.println("3 - Remover usuário");
        System.out.println("4 - Consultar todos os cadastros");
        System.out.println("5 - Consultar usuário pelo CPF");
        System.out.println("6 - Consultar usuários pelas iniciais do nome");
        System.out.println("7 - Sair");
        System.out.print("Escolha uma opção: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Opção inválida!");
            return -1;
        }
    }

    // ** Captura os dados para criação de um novo usuário e retorna um objeto
    // Usuario
    public static Usuario capturarDadosUsuario() throws Exception {
        System.out.println("== Cadastro de Usuário ==");
        System.out.print("Digite o ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF (11 dígitos): ");
        String cpf = scanner.nextLine();
        System.out.print("Digite a Idade: ");
        int idade = Integer.parseInt(scanner.nextLine());
        return new Usuario(id, nome, cpf, idade);
    }

    // ** Captura o ID do usuário
    public static int capturarId() {
        System.out.print("Digite o ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    // ** Captura o CPF do usuário
    public static String capturarCpf() {
        System.out.print("Digite o CPF (11 dígitos): ");
        return scanner.nextLine();
    }

    // ** Captura as iniciais do nome do usuário
    public static String capturarIniciais() {
        System.out.print("Digite as iniciais do nome: ");
        return scanner.nextLine();
    }

    // ** Método para confirmar ações (por exemplo, remoção)
    public static boolean confirmarAcao(String mensagem) {
        System.out.print(mensagem + " (S/N): ");
        String resp = scanner.nextLine();
        return resp.equalsIgnoreCase("S");
    }

    public static Usuario capturarDadosEdicao(int id) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o novo Nome: ");
        String nome = sc.nextLine();
        System.out.print("Digite o novo CPF (11 dígitos): ");
        String cpf = sc.nextLine();
        System.out.print("Digite a nova Idade: ");
        int idade = Integer.parseInt(sc.nextLine());
        return new Usuario(id, nome, cpf, idade);
    }

}
