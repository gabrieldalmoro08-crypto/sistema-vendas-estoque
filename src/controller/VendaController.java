package controller;

import service.*;
import view.*;
import model.*;

import java.util.ArrayList;
import java.util.List;

public class VendaController {

    VendaService vendaService;
    VendaView vendaView;

    public VendaController(VendaService vendaService, VendaView vendaView) {
        this.vendaService = vendaService;
        this.vendaView = vendaView;
    }

    public void iniciarMenuVendas() {
        int opcao = 0;

        while (opcao != 5) {
            opcao = vendaView.menuVenda();

            switch (opcao) {
                case 1:
                    realizarVenda();
                    break;
                case 2:
                    excluirVenda();
                    break;
                case 3:
                    buscarVendaID();
                    break;
                case 4:
                    listarVendas();
                    break;
                case 5:
                    vendaView.exibirMensagem("Voltando ao menu anterior...");
                    break;
                default:
                    vendaView.exibirMensagem("Opção inválida! Tente novamente.");
            }
        }
    }

    public void realizarVenda() {
        vendaView.exibirMensagem("\n--- TELA DE REALIZAR VENDA ---");

        try {
            Venda novaVenda = new Venda();

            int idCliente = vendaView.pedirIdCliente();
            Cliente cliente = new Cliente();
            cliente.setId(idCliente);
            novaVenda.setCliente(cliente);

            List<ItemVenda> carrinho = new ArrayList<>();
            boolean continuarComprando = true;

            while (continuarComprando) {
                int idProduto = vendaView.pedirIdProduto();
                int quantidade = vendaView.pedirQuantidade();

                Produto produto = new Produto();
                produto.setId(idProduto);

                ItemVenda item = new ItemVenda();
                item.setProduto(produto);
                item.setQuantidade(quantidade);

                carrinho.add(item);
                vendaView.exibirMensagem("[+] Produto adicionado ao carrinho!");

                continuarComprando = vendaView.confirmarAcao("Deseja adicionar mais produtos na venda?");
            }

            novaVenda.setItens(carrinho);
            vendaService.realizarVenda(novaVenda);

            vendaView.exibirMensagem("\n[SUCESSO] Venda realizada com sucesso! Valor Total: R$ " + novaVenda.getValorTotal());

        } catch (IllegalArgumentException e) {
            vendaView.exibirMensagem("\n[ERRO NA VENDA] " + e.getMessage());
        } catch (Exception e) {
            vendaView.exibirMensagem("\n[ERRO INESPERADO] " + e.getMessage());
        }
    }

    private void excluirVenda(){

        try {

            int id = vendaView.pedirIdVenda("Qual o ID da venda? ");
            Venda vendaEncontrada = vendaService.buscarVendaId(id);

            vendaView.exibirDetalhesVenda(vendaEncontrada);

            boolean confirmacao = vendaView.confirmarAcao("Tem certeza que deseja cancelar esta venda e devolver o estoque?");

            if (confirmacao) {
                vendaService.excluirVenda(vendaEncontrada);
                vendaView.exibirMensagem("\n[SUCESSO] Venda cancelada com sucesso!");
            } else {
                vendaView.exibirMensagem("\n[AVISO] Cancelamento abortado pelo usuário.");
            }

        } catch (IllegalArgumentException e) {
            vendaView.exibirMensagem("\n[ERRO NA VENDA] " + e.getMessage());
        } catch (Exception e) {
            vendaView.exibirMensagem("\n[ERRO INESPERADO] " + e.getMessage());
        }

    }

    private void listarVendas(){
        try{
            List<Venda> lista = vendaService.listarTodasVendas();
            vendaView.exibirListaVendas(lista);
        }catch (IllegalArgumentException e) {
            vendaView.exibirMensagem("\n[ERRO NA VENDA] " + e.getMessage());
        } catch (Exception e) {
            vendaView.exibirMensagem("\n[ERRO INESPERADO] " + e.getMessage());
        }
    }

    private void buscarVendaID(){
        try{
            int idVenda = vendaView.pedirIdVenda("Qual o id da venda? ");
            Venda venda = vendaService.buscarVendaId(idVenda);

            vendaView.exibirDetalhesVenda(venda);

        }catch (IllegalArgumentException e) {
            vendaView.exibirMensagem("\n[ERRO NA VENDA] " + e.getMessage());
        } catch (Exception e) {
            vendaView.exibirMensagem("\n[ERRO INESPERADO] " + e.getMessage());
        }
    }
}
