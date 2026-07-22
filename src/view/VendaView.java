package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import service.VendaService;
import model.Venda;
import model.ItemVenda;
import model.Cliente;
import model.Produto;

public class VendaView {

    private Scanner entrada = new Scanner(System.in);
    private VendaService vendaService;

    public VendaView(VendaService vendaService) {
        this.vendaService = vendaService;
    }

    public int menuVenda() {
        String menu = "\n------- GERENCIAMENTO DE VENDAS -------\n"
                + "1. Realizar Venda\n"
                + "2. Excluir/Cancelar Venda\n"
                + "3. Buscar venda por ID\n"
                + "4. Listar todas as vendas\n"
                + "5. Voltar ao menu anterior\n";

        System.out.println(menu);
        System.out.print("Escolha uma opção: ");
        return Integer.parseInt(entrada.nextLine());
    }

    public void realizarVenda() {
        System.out.println("\n--- TELA DE REALIZAR VENDA ---");

        try {
            Venda novaVenda = new Venda();

            System.out.print("Insira o ID do cliente: ");
            int idCliente = Integer.parseInt(entrada.nextLine());

            Cliente cliente = new Cliente();
            cliente.setId(idCliente);
            novaVenda.setCliente(cliente);

            List<ItemVenda> carrinho = new ArrayList<>();
            boolean continuarComprando = true;

            while (continuarComprando) {
                System.out.print("\nDigite o ID do produto: ");
                int idProduto = Integer.parseInt(entrada.nextLine());

                System.out.print("Digite a quantidade desejada: ");
                int quantidade = Integer.parseInt(entrada.nextLine());

                Produto produto = new Produto();
                produto.setId(idProduto);

                ItemVenda item = new ItemVenda();
                item.setProduto(produto);
                item.setQuantidade(quantidade);

                carrinho.add(item);
                System.out.println("[+] Produto adicionado ao carrinho!");

                System.out.print("Deseja adicionar mais produtos? (S/N): ");
                String resposta = entrada.nextLine();
                if (resposta.equalsIgnoreCase("N")) {
                    continuarComprando = false;
                }
            }

            novaVenda.setItens(carrinho);
            vendaService.realizarVenda(novaVenda);

            System.out.println("\n[SUCESSO] Venda realizada com sucesso! Valor Total: R$ " + novaVenda.getValorTotal());

        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERRO NA VENDA] " + e.getMessage());
        } catch (Exception e) {
            System.out.println("\n[ERRO INESPERADO] " + e.getMessage());
        }
    }

    public void excluirVenda() {
        System.out.println("\n--- TELA DE CANCELAMENTO DE VENDA ---");
        System.out.print("Digite o ID da venda que deseja cancelar (o estoque será devolvido): ");
        int idVenda = Integer.parseInt(entrada.nextLine());

        try {
            Venda venda = new Venda();
            venda.setId(idVenda);

            vendaService.excluirVenda(venda);
            System.out.println("\n[SUCESSO] Venda cancelada e estoque devolvido com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERRO] " + e.getMessage());
        }
    }

    public void buscarVendaPorId() {
        System.out.print("\nDigite o ID da venda: ");
        int idVenda = Integer.parseInt(entrada.nextLine());

        try {
            Venda venda = vendaService.buscarVendaId(idVenda);
            exibirDetalhesVenda(venda);
        } catch (IllegalArgumentException e) {
            System.out.println("\n[ERRO] " + e.getMessage());
        }
    }

    public void listarTodasVendas() {
        List<Venda> vendas = vendaService.listarTodasVendas();

        if (vendas == null || vendas.isEmpty()) {
            System.out.println("\n[!] Nenhuma venda registrada no sistema.");
            return;
        }

        System.out.println("\n--- HISTÓRICO DE VENDAS ---");
        for (Venda v : vendas) {
            exibirDetalhesVenda(v);
        }
    }

    private void exibirDetalhesVenda(Venda venda) {
        System.out.println("------------------------------------------------");
        System.out.println("ID Venda: " + venda.getId());
        System.out.println("Cliente ID: " + (venda.getCliente() != null ? venda.getCliente().getId() : "Desconhecido"));
        System.out.println("Valor Total: R$ " + venda.getValorTotal());
        System.out.println("Itens da Venda:");
        if (venda.getItens() != null) {
            for (ItemVenda item : venda.getItens()) {
                System.out.println("  - Prod ID: " + item.getProduto().getId() + " | Qtd: " + item.getQuantidade() + " | Subtotal: R$ " + (item.getQuantidade() * item.getPrecoUnitario()));
            }
        }
        System.out.println("------------------------------------------------");
    }
}