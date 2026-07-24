package controller;

import service.*;
import view.*;
import model.*;

import java.util.List;

public class ProdutoController {

    private ProdutoService produtoService;
    private ProdutoView produtoView;

    public ProdutoController(ProdutoService produtoService, ProdutoView produtoView) {
        this.produtoService = produtoService;
        this.produtoView = produtoView;
    }

    public void iniciarMenuProdutos() {
        int opcao = 0;

        while (opcao != 7) {
            opcao = produtoView.menuProduto();

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    excluirProduto();
                    break;
                case 3:
                    atualizarProduto();
                    break;
                case 4:
                    listarProdutos();
                    break;
                case 5:
                    buscarPorId();
                    break;
                case 6:
                    buscarPorDescricao();
                    break;
                case 7:
                    produtoView.exibirMensagem("Voltando ao menu anterior...");
                    break;
                default:
                    produtoView.exibirMensagem("Opção inválida! Tente novamente.");
            }
        }
    }

    private void cadastrarProduto() {
        try {
            Produto novoProduto = produtoView.cadastrarNovoProduto();

            produtoService.cadastroProdutos(novoProduto);
            produtoView.exibirMensagem("Produto cadastrado com sucesso!");

        } catch (IllegalArgumentException e) {
            produtoView.exibirMensagem("Aviso: " + e.getMessage());
        } catch (Exception e) {
            produtoView.exibirMensagem("Erro Crítico no Sistema: " + e.getMessage());
        }
    }

    private void excluirProduto() {
        try {
            int id = produtoView.pedirIdProduto();

            Produto produto = produtoService.buscarPorProdutoId(id);

            if (produto == null) {
                produtoView.exibirMensagem("Aviso: Nenhum produto encontrado com o ID " + id);
                return;
            }

            String pergunta = "Tem certeza que deseja excluir o produto ID: " + id + " ( " + produto.getDescricao() + "?";

            if (produtoView.confirmarAcao(pergunta)) {
                produtoService.exclusaoProduto(produto);
                produtoView.exibirMensagem("Produto excluído com sucesso! ");
            } else {
                produtoView.exibirMensagem("Operação de exclusão cancelada pelo usuário. ");
            }

        } catch (IllegalArgumentException e) {
            produtoView.exibirMensagem("Aviso: " + e.getMessage());
        } catch (Exception e) {
            produtoView.exibirMensagem("Erro Crítico no Sistema: " + e.getMessage());
        }
    }

    private void atualizarProduto() {
        try {
            Produto produtoAtualizado = produtoView.atualizarProduto();

            produtoService.atualizacaoProduto(produtoAtualizado);
            produtoView.exibirMensagem("Cadastro atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            produtoView.exibirMensagem("Aviso: " + e.getMessage());

        } catch (Exception e) {
            produtoView.exibirMensagem("Erro Crítico no Sistema: " + e.getMessage());
        }
    }

    private void listarProdutos() {
        try {
            List<Produto> lista = produtoService.listarProdutos();
            produtoView.exibirListaProdutos(lista);
        } catch (Exception e) {
            produtoView.exibirMensagem("Erro ao carregar lista: " + e.getMessage());
        }
    }

    private void buscarPorId() {
        try {
            int id = produtoView.pedirIdProduto();
            Produto produto = produtoService.buscarPorProdutoId(id);

            if (produto != null) {
                produtoView.exibirProduto(produto);
            } else {
                produtoView.exibirMensagem("Nenhum produto encontrado com este ID.");
            }
        } catch (IllegalArgumentException e) {
            produtoView.exibirMensagem("Erro na busca: " + e.getMessage());
        } catch (Exception e) {
            produtoView.exibirMensagem("Erro Crítico no Sistema: " + e.getMessage());
        }
    }

    private void buscarPorDescricao() {
        try {
            String termo = produtoView.pedirTexto("Digite o nome ou parte do nome do produto: ");

            List<Produto> resultados = produtoService.pesquisarPorDescricao(termo);

            if (resultados.isEmpty()) {
                produtoView.exibirMensagem("Aviso: Nenhum produto encontrado contendo '" + termo + "'.");
            } else {
                produtoView.exibirMensagem("Foram encontrados " + resultados.size() + " produto(s):");
                produtoView.exibirListaProdutos(resultados);
            }

        } catch (IllegalArgumentException e) {
            produtoView.exibirMensagem("Erro na busca: " + e.getMessage());
        } catch (Exception e) {
            produtoView.exibirMensagem("Erro Crítico no Sistema: " + e.getMessage());
        }
    }
}
