package controller;

import model.Usuario;
import model.Administrador;
import view.MainView;

public class MenuPrincipalController {

    private MainView mainView;

    private UsuarioController usuarioController;
    private ProdutoController produtoController;
    private VendaController vendaController;

    public MenuPrincipalController(MainView mainView, UsuarioController usuarioController,
                                   ProdutoController produtoController, VendaController vendaController) {
        this.mainView = mainView;
        this.usuarioController = usuarioController;
        this.produtoController = produtoController;
        this.vendaController = vendaController;
    }

    public void iniciarSistema() {
        int opcao = 0;

        while (opcao != 3) {
            opcao = mainView.menuPrincipal();

            switch (opcao) {
                case 1:
                    Usuario usuarioLogado = usuarioController.realizarLogin();
                    if (usuarioLogado != null) {
                        direcionarPorPerfil(usuarioLogado);
                    }
                    break;
                case 2:
                    usuarioController.cadastrarUsuario();
                    break;
                case 3:
                    mainView.exibirMensagem("\nEncerrando o sistema... Até logo!");
                    break;
                default:
                    mainView.exibirMensagem("Opção inválida!");
            }
        }
    }

    private void direcionarPorPerfil(Usuario usuarioLogado) {
        boolean isAdministrador = usuarioLogado instanceof Administrador;

        if (isAdministrador) {
            iniciarPainelAdmin();
        } else {
            iniciarPainelCliente(usuarioLogado);
        }
    }

    private void iniciarPainelAdmin() {
        int opcao = 0;
        while (opcao != 5) {
            opcao = mainView.menuAdministrador();
            switch (opcao) {
                case 1:
                    usuarioController.iniciarMenuUsuarios();
                    break;
                case 2:
                    produtoController.iniciarMenuProdutos();
                    break;
                case 3:
                    vendaController.realizarVenda(); // Compra pelo sistema administrativo
                    break;
                case 4:
                    vendaController.iniciarMenuVendas();
                    break;
                case 5:
                    mainView.exibirMensagem("\nFazendo logout...");
                    break;
                default:
                    mainView.exibirMensagem("Opção inválida!");
            }
        }
    }

    private void iniciarPainelCliente(Usuario usuarioLogado) {
        int opcao = 0;
        while (opcao != 3) {
            opcao = mainView.menuCliente();
            switch (opcao) {
                case 1:
                    mainView.exibirMensagem("\nMódulo de perfil em construção...");
                    break;
                case 2:
                    vendaController.realizarVenda();
                    break;
                case 3:
                    mainView.exibirMensagem("\nFazendo logout...");
                    break;
                default:
                    mainView.exibirMensagem("Opção inválida!");
            }
        }
    }
}