package app;

import screens.TelaUsuario;
import service.UsuarioService;
import repository.UsuarioRepositoryImpl;
import entity.Usuario;
import java.util.List;
import java.util.Scanner;

//** Classe principal que orquestra a execução do programa, chamando as telas e os serviços
public class Main {
    public static void main(String[] args) {
        // ** Inicializa os componentes
        UsuarioService service = new UsuarioService(new UsuarioRepositoryImpl());
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            opcao = TelaUsuario.exibirMenu();
            switch (opcao) {
                case 1:
                    try {
                        // ** Chama a tela de cadastro e o service para cadastrar
                        Usuario novoUsuario = TelaUsuario.capturarDadosUsuario();
                        if (service.cadastrarUsuario(novoUsuario)) {
                            System.out.println("Usuário cadastrado com sucesso!");
                        } else {
                            System.out.println(
                                    "Erro ao cadastrar usuário. Verifique se já existe cadastro com o mesmo CPF.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro no cadastro: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        // ** Listar todos os usuários para seleção (no repositório real você buscaria
                        // do banco)
                        List<Usuario> usuarios = new UsuarioRepositoryImpl().findAll();
                        exibirUsuarios(usuarios);
                        if (usuarios.isEmpty()) {
                            break;
                        }
                        System.out.print("Digite o ID do usuário a ser editado: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Usuario usuarioExistente = new UsuarioRepositoryImpl().findById(id);
                        if (usuarioExistente == null) {
                            System.out.println("Usuário não encontrado para o ID: " + id);
                            break;
                        }
                        // ** Captura novos dados
                        System.out.print("Digite o novo Nome: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite o novo CPF (11 dígitos): ");
                        String cpf = scanner.nextLine();
                        System.out.print("Digite a nova Idade: ");
                        int idade = Integer.parseInt(scanner.nextLine());
                        Usuario usuarioAtualizado = new Usuario(id, nome, cpf, idade);
                        if (service.atualizarUsuario(usuarioAtualizado)) {
                            System.out.println("Usuário atualizado com sucesso!");
                        } else {
                            System.out.println("Erro ao atualizar usuário.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro na edição: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        // ** Remoção: lista usuários, seleciona o usuário e confirma a remoção
                        List<Usuario> usuarios = new UsuarioRepositoryImpl().findAll();
                        exibirUsuarios(usuarios);
                        if (usuarios.isEmpty()) {
                            break;
                        }
                        System.out.print("Digite o ID do usuário a ser removido: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        Usuario usuarioRemover = new UsuarioRepositoryImpl().findById(id);
                        if (usuarioRemover == null) {
                            System.out.println("Usuário não encontrado para o ID: " + id);
                            break;
                        }
                        System.out.println("Dados do usuário: " + usuarioRemover);
                        if (TelaUsuario.confirmarAcao("Deseja realmente remover o usuário?")) {
                            if (new UsuarioRepositoryImpl().delete(id)) {
                                System.out.println("Usuário removido com sucesso!");
                            } else {
                                System.out.println("Erro ao remover usuário.");
                            }
                        } else {
                            System.out.println("Remoção cancelada.");
                        }
                    } catch (Exception e) {
                        System.out.println("Erro na remoção: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        // ** Consulta todos os cadastros
                        List<Usuario> usuarios = new UsuarioRepositoryImpl().findAll();
                        exibirUsuarios(usuarios);
                    } catch (Exception e) {
                        System.out.println("Erro ao consultar usuários: " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        // ** Consulta usuário pelo CPF
                        String cpf = TelaUsuario.capturarCpf();
                        Usuario usuario = new UsuarioRepositoryImpl().findByCpf(cpf);
                        if (usuario == null) {
                            System.out.println("Usuário não encontrado para o CPF: " + cpf);
                        } else {
                            System.out.println(usuario);
                        }
                    } catch (Exception e) {
                        System.out.println("Erro na consulta por CPF: " + e.getMessage());
                    }
                    break;
                case 6:
                    try {
                        // ** Consulta usuários pelas iniciais do nome
                        String iniciais = TelaUsuario.capturarIniciais();
                        List<Usuario> usuarios = new UsuarioRepositoryImpl().findByNomeInicial(iniciais);
                        exibirUsuarios(usuarios);
                        if (usuarios.isEmpty()) {
                            System.out.println("Nenhum usuário encontrado para as iniciais: " + iniciais);
                        }
                    } catch (Exception e) {
                        System.out.println("Erro na consulta por iniciais: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        } while (opcao != 7);
        scanner.close();
    }

    private static void exibirUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado na base de dados.");
        } else {
            for (Usuario u : usuarios) {
                System.out.println(u);
            }
        }
    }
}
