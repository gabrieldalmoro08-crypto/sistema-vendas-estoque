package view;

import java.util.Scanner;

public class MainView {

    private Scanner entrada = new Scanner(System.in);

    public int menuPrincipal() {
        String menu = "------- MENU DE OPÇÕES! -------\n"
                + "\n1. Login"
                + "\n2. Realizar cadastro"
                + "\n3. Sair\n";

        System.out.println(menu);
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(entrada.nextLine());
    }

    public int menuAdministrador() {
        String menu = "------- PAINEL DO ADMINISTRADOR -------\n"
                + "\n1. Gerenciar Usuários"
                + "\n2. Gerenciar Produtos"
                + "\n3. Realizar Compra"
                + "\n4. Gerenciar Vendas"
                + "\n5. Sair (Fazer Logout)\n";

        System.out.println(menu);
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(entrada.nextLine());
    }

    public int menuCliente() {
        String menu = "------- PAINEL DO CLIENTE -------\n"
                + "\n1. Meu Perfil (Atualizar/Ver)"
                + "\n2. Realizar Compra"
                + "\n3. Sair (Fazer Logout)\n";

        System.out.println(menu);
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(entrada.nextLine());
    }
}
