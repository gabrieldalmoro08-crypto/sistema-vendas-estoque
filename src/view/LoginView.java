package view;

import java.util.Scanner;

public class LoginView {

    private Scanner entrada = new Scanner(System.in);

    public void exibirCabecalho() {
        System.out.println("=====================================");
        System.out.println("        BEM-VINDO AO SISTEMA         ");
        System.out.println("=====================================");
    }

    public String pedirCpf() {
        System.out.print("\nDigite seu CPF: ");
        return entrada.nextLine();
    }

    public String pedirSenha() {
        System.out.print("Digite sua Senha: ");
        return entrada.nextLine();
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }
}