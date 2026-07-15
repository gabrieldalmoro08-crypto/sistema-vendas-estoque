package view;

import java.util.Scanner;

public class UsuarioView {

    private Scanner entrada = new Scanner(System.in);

    public int menuUsuario() {
        String menu = "------- GERENCIAMENTO DE USUÁRIOS -------\n"
                + "\n1. Cadastrar Usuário"
                + "\n2. Excluir Usuário"
                + "\n3. Atualizar cadastro de Usuário"
                + "\n4. Listar todos os usuários"
                + "\n5. Buscar usuário por ID"
                + "\n6. Buscar usuário por CPF"
                + "\n7. Voltar ao menu anterior\n";

        System.out.println(menu);
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(entrada.nextLine());
    }


}