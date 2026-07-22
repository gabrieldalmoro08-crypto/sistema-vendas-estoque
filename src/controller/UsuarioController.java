package controller;

import model.Cliente;
import model.Usuario;
import service.AutenticacaoService;
import service.UsuarioService;
import view.UsuarioView;

import java.util.List;
import java.util.Scanner;

public class UsuarioController {

    private UsuarioView usuarioView;
    private UsuarioService usuarioService;
    private AutenticacaoService authService;
    private Scanner entrada;

    public UsuarioController(UsuarioView usuarioView, UsuarioService usuarioService, AutenticacaoService authService) {
        this.usuarioView = usuarioView;
        this.usuarioService = usuarioService;
        this.authService = authService;
        this.entrada = new Scanner(System.in);
    }

    public Usuario realizarLogin() {
        System.out.print("\n--- TELA DE LOGIN ---");
        System.out.print("\nDigite seu CPF: ");
        String cpf = entrada.nextLine();

        System.out.print("Digite sua Senha: ");
        String senha = entrada.nextLine();

        try {
            return authService.fazerLogin(cpf, senha);
        } catch (IllegalArgumentException e) {
            usuarioView.exibirMensagem("Falha no login: " + e.getMessage());
            return null;
        }
    }

    public void iniciarMenuUsuarios() {
        int opcao = 0;

        while (opcao != 7) {
            opcao = usuarioView.menuUsuario();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    excluirUsuario();
                    break;
                case 3:
                    atualizarUsuario();
                    break;
                case 4:
                    listarUsuarios();
                    break;
                case 5:
                    buscarPorId();
                    break;
                case 6:
                    buscarPorCpf();
                    break;
                case 7:
                    usuarioView.exibirMensagem("Voltando ao menu anterior...");
                    break;
                default:
                    usuarioView.exibirMensagem("Opção inválida! Tente novamente.");
            }
        }
    }

    private void cadastrarUsuario() {
        try {
            System.out.print("\nQual perfil deseja cadastrar? (1 - Cliente | 2 - Administrador): ");
            int perfil = Integer.parseInt(entrada.nextLine());

            Usuario novoUsuario;
            if (perfil == 1) {
                novoUsuario = usuarioView.cadastroNovoCliente();
            } else if (perfil == 2) {
                novoUsuario = usuarioView.cadastroNovoAdministrador();
            } else {
                usuarioView.exibirMensagem("Opção de perfil inválida.");
                return;
            }

            usuarioService.cadastrarUsuario(novoUsuario); // Manda pro Service salvar
            usuarioView.exibirMensagem("Usuário cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            usuarioView.exibirMensagem("Erro de Validação: " + e.getMessage());
        } catch (Exception e) {
            usuarioView.exibirMensagem("Erro Inesperado: " + e.getMessage());
        }
    }

    private void excluirUsuario() {
        try {
            int id = usuarioView.pedirIdUsuario();

            if (usuarioView.confirmarAcao("Tem certeza que deseja excluir o usuário de ID " + id + "?")) {
                Usuario usuarioParaExcluir = new Cliente();
                usuarioParaExcluir.setId(id);

                usuarioService.excluirUsuario(usuarioParaExcluir);
                usuarioView.exibirMensagem("Usuário excluído com sucesso!");
            }
        } catch (IllegalArgumentException e) {
            usuarioView.exibirMensagem("Erro ao excluir: " + e.getMessage());
        }
    }

    private void atualizarUsuario() {
        try {
            Usuario usuarioAtualizado = usuarioView.atualizarUsuario();

            usuarioService.atualizarUsuario(usuarioAtualizado);
            usuarioView.exibirMensagem("Cadastro atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            usuarioView.exibirMensagem("Erro ao atualizar: " + e.getMessage());
        }
    }

    private void listarUsuarios() {
        try {
            List<Usuario> lista = usuarioService.listarTodosUsuarios();
            usuarioView.exibirListaUsuarios(lista);
        } catch (Exception e) {
            usuarioView.exibirMensagem("Erro ao carregar lista: " + e.getMessage());
        }
    }

    private void buscarPorId() {
        try {
            int id = usuarioView.pedirIdUsuario();
            Usuario usuario = usuarioService.usuarioBuscarPorID(id);

            if (usuario != null) {
                usuarioView.exibirUsuario(usuario);
            } else {
                usuarioView.exibirMensagem("Nenhum usuário encontrado com este ID.");
            }
        } catch (IllegalArgumentException e) {
            usuarioView.exibirMensagem("Erro na busca: " + e.getMessage());
        }
    }

    private void buscarPorCpf() {
        try {
            String cpf = usuarioView.pedirCPFUsuario();
            Usuario usuario = usuarioService.usuarioBuscarPorCPF(cpf);

            if (usuario != null) {
                usuarioView.exibirUsuario(usuario);
            } else {
                usuarioView.exibirMensagem("Nenhum usuário encontrado com este CPF.");
            }
        } catch (IllegalArgumentException e) {
            usuarioView.exibirMensagem("Erro na busca: " + e.getMessage());
        }
    }
}