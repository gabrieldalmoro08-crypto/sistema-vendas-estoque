package service;

import model.*;
import dao.*;
import controller.*;

import java.util.List;

public class ProdutoService {

    private ProdutoDAO produtoDAO;

    public ProdutoService(ProdutoDAO produtoDAO) {
        this.produtoDAO = produtoDAO;
    }

    public void cadastroProdutos(Produto produto){

        validarDadosBasicosProduto(produto);

        if(produto.getQtde() <= 0){
            throw new IllegalArgumentException("Erro: A quantidade inicial em estoque não pode ser negativa!");
        }

        Produto produtoExistente = produtoDAO.buscarPorDescricao(produto.getDescricao());
        if (produtoExistente != null) {
            throw new IllegalArgumentException("Erro: Já existe um produto cadastrado com a descrição '" + produto.getDescricao() + "'!");
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

    public void atualizacaoProduto(Produto produto){

        if(produto == null || produto.getId() <= 0){
            throw new IllegalArgumentException("Erro: Produto inválido ou não selecionado para edição!");
        }

        validarDadosBasicosProduto(produto);

        if(produto.getQtde() < 0){
            throw new IllegalArgumentException("Erro: Quantidade em estoque não pode ser negativa!");
        }

        produtoDAO.atualizarProduto(produto);

    }

    private void validarDadosBasicosProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Erro: Os dados do produto estão vazios!");
        }

        if (produto.getPreco() <= 0) {
            throw new IllegalArgumentException("Erro: O preço do produto deve ser maior que zero!");
        }

        if (produto.getDescricao() == null || produto.getDescricao().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O nome do produto não pode ficar em branco!");
        }
    }

    public Produto buscarPorProdutoId(int id){

        if(id <= 0){
            throw new IllegalArgumentException("Erro: Id inválido para busca!");
        }
        return produtoDAO.buscarProdutoPorId(id);
    }

    public List<Produto> listarProdutos(){
        return produtoDAO.listarTodosProdutos();
    }

    public List<Produto> pesquisarPorDescricao(String termo) {
        if (termo == null || termo.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O termo de pesquisa não pode estar vazio!");
        }

        return produtoDAO.pesquisarPorParteDaDescricao(termo);
    }

}
