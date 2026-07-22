package view;

import java.util.List;
import java.util.Scanner;
import service.*;
import model.*;
import dao.*;

public class ProdutoView {

    private Scanner entrada = new Scanner(System.in);

    public int menuProduto() {
        String menu = "------- GERENCIAMENTO DE PRODUTOS -------\n"
                + "\n1. Cadastrar Produto"
                + "\n2. Excluir Produto"
                + "\n3. Atualizar cadastro de Produto"
                + "\n4. Listar todos os produtos"
                + "\n5. Buscar produto por ID"
                + "\n6. Voltar ao menu anterior\n";

        System.out.println(menu);
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(entrada.nextLine());
    }

    private void preencherDados(Produto produto){
        System.out.print("Insira a descrição(nome) do produto: ");
        produto.setDescricao(entrada.nextLine());

        System.out.print("Insira o preço: "); // Ajustado para print
        produto.setPreco(Double.parseDouble(entrada.nextLine()));

        System.out.print("Insira a quantidade: ");
        produto.setQtde(Integer.parseInt(entrada.nextLine()));
    }

    public Produto cadastrarNovoProduto(){
        System.out.println("\n--- TELA DE CADASTRO DE PRODUTO ---");
        Produto novoProduto = new Produto();
        preencherDados(novoProduto);
        return novoProduto;
    }

    public Produto  atualizarProduto(){
        System.out.println("\n--- TELA DE ATUALIZAÇÃO DE PRODUTO ---");
        System.out.print("Insira o ID do produto que deseja atualizar: ");
        int id = Integer.parseInt(entrada.nextLine());

        Produto produtoAtualizado = new Produto();
        produtoAtualizado.setId(id);
        preencherDados(produtoAtualizado);

        return produtoAtualizado;
    }

    public int pedirIdProduto() {
        System.out.print("Digite o ID do produto: ");
        return Integer.parseInt(entrada.nextLine());
    }

    public void exibirProduto(Produto produto) {
        if (produto == null) {
            System.out.println("\n[!] Produto não encontrado.");
            return;
        }
        System.out.println("------------------------------------------------");
        System.out.println("ID: " + produto.getId() + " | Produto: " + produto.getDescricao());
        System.out.println("Preço: R$ " + produto.getPreco() + " | Em estoque: " + produto.getQtde());
        System.out.println("------------------------------------------------");
    }

    public void exibirListaProdutos(List<Produto> produtos) {
        if (produtos == null || produtos.isEmpty()) {
            System.out.println("\n[!] Nenhum produto cadastrado no estoque.");
            return;
        }
        System.out.println("\n--- LISTA DE PRODUTOS ---");
        for (Produto p : produtos) {
            exibirProduto(p);
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
