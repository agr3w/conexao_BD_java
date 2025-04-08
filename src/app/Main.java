package app;

import screens.TelaUsuario;
import service.UsuarioService;
import repository.UsuarioRepositoryImpl;
import entity.Usuario;
import util.ErrorHandler;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UsuarioService service = new UsuarioService(new UsuarioRepositoryImpl());
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        do {
            opcao = TelaUsuario.exibirMenu();
            switch (opcao) {
                case 1:
                    try {
                        Usuario novoUsuario = TelaUsuario.capturarDadosUsuario();
                        if (service.cadastrarUsuario(novoUsuario)) {
                            System.out.println("Usuário cadastrado com sucesso!");
                        } else {
                            System.out.println("Erro ao cadastrar usuário. Verifique se já existe um cadastro com o mesmo CPF.");
                        }
                    } catch (Exception e) {
                        ErrorHandler.handle(e, "Erro no cadastro");
                    }
                    break;
                case 2:
                    try {
                        List<Usuario> usuarios = service.listarUsuarios();
                        exibirUsuarios(usuarios);
                        if (usuarios.isEmpty()) break;

                        System.out.print("Digite o ID do usuário a ser editado: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        Usuario usuarioExistente = service.buscarPorId(id);
                        if (usuarioExistente == null) {
                            System.out.println("Usuário não encontrado para o ID: " + id);
                            break;
                        }

                        Usuario usuarioAtualizado = TelaUsuario.capturarDadosEdicao(id);
                        if (service.atualizarUsuario(usuarioAtualizado)) {
                            System.out.println("Usuário atualizado com sucesso!");
                        } else {
                            System.out.println("Erro ao atualizar usuário.");
                        }
                    } catch (Exception e) {
                        ErrorHandler.handle(e, "Erro na edição");
                    }
                    break;
                case 3:
                    try {
                        List<Usuario> usuarios = service.listarUsuarios();
                        exibirUsuarios(usuarios);
                        if (usuarios.isEmpty()) break;

                        System.out.print("Digite o ID do usuário a ser removido: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        Usuario usuarioRemover = service.buscarPorId(id);
                        if (usuarioRemover == null) {
                            System.out.println("Usuário não encontrado para o ID: " + id);
                            break;
                        }

                        System.out.println("Dados do usuário: " + usuarioRemover);
                        if (TelaUsuario.confirmarAcao("Deseja realmente remover o usuário?")) {
                            if (service.removerUsuario(id)) {
                                System.out.println("Usuário removido com sucesso!");
                            } else {
                                System.out.println("Erro ao remover usuário.");
                            }
                        } else {
                            System.out.println("Remoção cancelada.");
                        }
                    } catch (Exception e) {
                        ErrorHandler.handle(e, "Erro na remoção");
                    }
                    break;
                case 4:
                    try {
                        List<Usuario> usuarios = service.listarUsuarios();
                        exibirUsuarios(usuarios);
                    } catch (Exception e) {
                        ErrorHandler.handle(e, "Erro ao consultar usuários");
                    }
                    break;
                case 5:
                    try {
                        Usuario usuario = service.buscarPorCpf(TelaUsuario.capturarCpf());
                        if (usuario == null) {
                            System.out.println("Usuário não encontrado.");
                        } else {
                            System.out.println(usuario);
                        }
                    } catch (Exception e) {
                        ErrorHandler.handle(e, "Erro na consulta por CPF");
                    }
                    break;
                case 6:
                    try {
                        List<Usuario> usuarios = service.buscarPorIniciais(TelaUsuario.capturarIniciais());
                        exibirUsuarios(usuarios);
                        if (usuarios.isEmpty()) {
                            System.out.println("Nenhum usuário encontrado com essas iniciais.");
                        }
                    } catch (Exception e) {
                        ErrorHandler.handle(e, "Erro na consulta por iniciais");
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
