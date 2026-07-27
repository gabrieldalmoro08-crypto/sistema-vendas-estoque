package view;

import java.util.List;
import java.util.Scanner;
import model.Venda;
import model.ItemVenda;

public class VendaView {

    private Scanner entrada = new Scanner(System.in);

    // Veja que o VendaService SUMIU daqui! A View não fala mais com o Service.

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

    public int pedirIdCliente() {
        System.out.print("Insira o ID do cliente: ");
        return Integer.parseInt(entrada.nextLine());
    }

    public int pedirIdProduto() {
        System.out.print("Digite o ID do produto: ");
        return Integer.parseInt(entrada.nextLine());
    }

    public int pedirQuantidade() {
        System.out.print("Digite a quantidade desejada: ");
        return Integer.parseInt(entrada.nextLine());
    }

    public int pedirIdVenda(String contexto) {
        System.out.print("\n" + contexto);
        return Integer.parseInt(entrada.nextLine());
    }

    public void exibirMensagem(String mensagem) {
        System.out.println(mensagem);
    }

    public boolean confirmarAcao(String mensagem) {
        System.out.print(mensagem + " (S/N): ");
        return entrada.nextLine().equalsIgnoreCase("S");
    }

    public void exibirDetalhesVenda(Venda venda) {
        if (venda == null) {
            System.out.println("\n[!] Venda não encontrada.");
            return;
        }
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

    public void exibirListaVendas(List<Venda> vendas) {
        if (vendas == null || vendas.isEmpty()) {
            System.out.println("\n[!] Nenhuma venda registrada no sistema.");
            return;
        }

        System.out.println("\n--- HISTÓRICO DE VENDAS ---");
        for (Venda v : vendas) {
            exibirDetalhesVenda(v);
        }
    }
}