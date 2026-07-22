package view;

import model.Usuario;
import service.AutenticacaoService;
import java.util.Scanner;

public class LoginView {

    private AutenticacaoService autenticacaoService;
    private Scanner entrada;

    public LoginView(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
        this.entrada = new Scanner(System.in);
    }

    public Usuario iniciarTelaLogin() {
        System.out.println("=====================================");
        System.out.println("        BEM-VINDO AO SISTEMA         ");
        System.out.println("=====================================");

        while (true) {
            try {
                System.out.print("\nDigite seu CPF: ");
                String cpf = entrada.nextLine();

                System.out.print("Digite sua Senha: ");
                String senha = entrada.nextLine();

                Usuario usuarioLogado = autenticacaoService.fazerLogin(cpf, senha);

                System.out.println("\n[SUCESSO] Login realizado! Bem-vindo(a), " + usuarioLogado.getNome() + "!");

                return usuarioLogado;

            } catch (IllegalArgumentException e) {
                System.out.println("\n[ERRO] " + e.getMessage());
                System.out.println("Tente novamente.");
            }
        }
    }
}
