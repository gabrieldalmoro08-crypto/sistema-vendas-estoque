package view;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import service.*;
import model.*;
import dao.*;
import java.time.format.DateTimeFormatter;

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

    private void preencherDadosBasicos(Usuario usuario) {
        System.out.print("Insira o nome: ");
        usuario.setNome(entrada.nextLine());

        System.out.print("Insira o sobrenome: ");
        usuario.setSobrenome(entrada.nextLine());

        System.out.print("Insira o CPF: ");
        usuario.setCPF(entrada.nextLine());

        System.out.print("Insira a senha: ");
        usuario.setSenha(entrada.nextLine());

        System.out.print("Insira a data de nascimento (DD/MM/AAAA): ");
        String dataString = entrada.nextLine();

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        usuario.setDataNascimento(LocalDate.parse(dataString, formatador));
    }

    public Usuario cadastroNovoCliente() {
        System.out.println("\n--- TELA DE CADASTRO DE CLIENTE ---");
        Usuario novoCliente = new Cliente();
        preencherDadosBasicos(novoCliente);
        return novoCliente;
    }

    public Usuario cadastroNovoAdministrador() {
        System.out.println("\n--- TELA DE CADASTRO DE ADMINISTRADOR ---");
        Usuario novoAdmin = new Administrador();
        preencherDadosBasicos(novoAdmin);
        return novoAdmin;
    }

    public Usuario atualizarUsuario(){
        System.out.println("\n--- TELA DE ATUALIZAÇÃO DE USUÁRIO ---");

        System.out.print("Insira o ID do usuário que deseja atualizar: ");
        int id = Integer.parseInt(entrada.nextLine());

        System.out.print("Qual o tipo desse usuário? (1 - Cliente | 2 - Administrador): ");
        int tipoUsuario = Integer.parseInt(entrada.nextLine());

        Usuario usuarioAtualizado;
        if(tipoUsuario == 1){
            usuarioAtualizado = new Cliente();
        } else {
            usuarioAtualizado = new Administrador();
        }

        usuarioAtualizado.setId(id);

        preencherDadosBasicos(usuarioAtualizado);

        return usuarioAtualizado;
    }

    public int pedirIdUsuario(){
        System.out.print("Digite o ID do usuário: ");
        return Integer.parseInt(entrada.nextLine());
    }

    public String pedirCPFUsuario(){
        System.out.print("Digite o CPF do usuário (somente números ou formatado): ");
        return entrada.nextLine();
    }

    public void exibirUsuario(Usuario usuario) {
        if (usuario == null) {
            System.out.println("\n[!] Usuário não encontrado.");
            return;
        }

        String tipo = usuario.getClass().getSimpleName();

        System.out.println("------------------------------------------------");
        System.out.println("ID: " + usuario.getId() + " | Tipo: " + tipo);
        System.out.println("Nome: " + usuario.getNome() + " " + usuario.getSobrenome());
        System.out.println("CPF: " + usuario.getCPF());
        System.out.println("Data Nasc: " + usuario.getDataNascimento());
        System.out.println("------------------------------------------------");
    }

    public void exibirListaUsuarios(List<Usuario> usuarios) {
        if (usuarios == null || usuarios.isEmpty()) {
            System.out.println("\n[!] Nenhum usuário cadastrado no sistema.");
            return;
        }
        System.out.println("\n--- LISTA DE USUÁRIOS CADASTRADOS ---");
        for (Usuario u : usuarios) {
            exibirUsuario(u);
        }
    }

    public void exibirMensagem(String mensagem) {
        System.out.println("\n[SYSTEM] " + mensagem);
    }

    public boolean confirmarAcao(String mensagem) {
        System.out.print("\n[ATENÇÃO] " + mensagem + " (S/N): ");
        String resposta = entrada.nextLine();
        return resposta.equalsIgnoreCase("S");
    }
}