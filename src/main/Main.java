package main;

import dao.*;
import service.*;
import view.*;
import controller.*;

public class Main {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ProdutoDAO produtoDAO = new ProdutoDAO();
        VendaDAO vendaDAO = new VendaDAO();

        AutenticacaoService authService = new AutenticacaoService(usuarioDAO);
        UsuarioService usuarioService = new UsuarioService(usuarioDAO);
        ProdutoService produtoService = new ProdutoService(produtoDAO);

        VendaService vendaService = new VendaService(vendaDAO, produtoDAO);

        UsuarioView usuarioView = new UsuarioView();
        ProdutoView produtoView = new ProdutoView();
        VendaView vendaView = new VendaView();
        MainView mainView = new MainView();
        LoginView loginView = new LoginView();

        UsuarioController usuarioController = new UsuarioController(
                usuarioView,
                loginView,
                usuarioService,
                authService
        );
        ProdutoController produtoController = new ProdutoController(produtoService, produtoView);
        VendaController vendaController = new VendaController(vendaService, vendaView);

        MenuPrincipalController menuPrincipalController = new MenuPrincipalController(
                mainView,
                usuarioController,
                produtoController,
                vendaController
        );

        menuPrincipalController.iniciarSistema();
    }
}