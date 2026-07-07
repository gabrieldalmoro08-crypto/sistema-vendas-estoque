package service;

import model.*;
import service.*;
import dao.*;
import controller.*;

public class ProdutoService {

    private ProdutoDAO produtoDAO;

    public ProdutoService(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void cadastroProdutos(Produto produto){

        if(produto.getPreco() <= 0){
            throw new IllegalArgumentException("Erro: O preço do produto deve ser maior que zero!");
        }

        if(produto.getQtde() <= 0){
            throw new IllegalArgumentException("Erro: A quantidade inicial em estoque não pode ser negativa!");
        }

        if(produto.getDescricao() == null || produto.getDescricao().trim().isEmpty()){
            throw new IllegalArgumentException("Erro: O nome do produto não pode ficar em branco!");
        }
        produtoDAO.cadastrarProduto(produto);
    }

    public void exclusaoProduto(Produto produto){

        if(produto == null || produto.getId() <= 0){
            throw new IllegalArgumentException("Erro: Produto inválido ou não selecionado para exclusão!");
        }

        boolean presenteNoHistorico = produtoDAO.verificarSeProdutoTemVenda(produto.getId());

        if(presenteNoHistorico){
            throw new IllegalArgumentException("Erro: Não é possível excluir. Este produto possui registros no histórico de vendas!");
        }

        produtoDAO.excluirProduto(produto.getId());
    }

}
