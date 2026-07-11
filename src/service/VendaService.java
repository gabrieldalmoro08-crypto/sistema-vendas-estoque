package service;

import dao.*;
import model.*;

public class VendaService {

    private VendaDAO vendaDAO;
    private ProdutoDAO produtoDAO;

    public VendaService(VendaDAO vendaDAO, ProdutoDAO produtoDAO) {
        this.vendaDAO = vendaDAO;
        this.produtoDAO = produtoDAO;
    }

    public void realizarVenda(Venda venda){

        if(venda == null || venda.getCliente() == null ||venda.getCliente().getId() <= 0){
            throw new IllegalArgumentException("Erro: Cliente não localizado!");
        }

        if(venda.getItens() == null || venda.getItens().isEmpty()){
            throw new IllegalArgumentException("Erro: A venda deve possuir ao menos um produto!");
        }

        for(ItemVenda item : venda.getItens()){

            Produto produtoDaTela = item.getProduto();
            int qtdComprada = item.getQuantidade();

            Produto produtoDoBanco = produtoDAO.buscarProdutoPorId(produtoDaTela.getId());

            if(produtoDoBanco == null){
                throw new IllegalArgumentException("Erro: Produto não encontrado!");
            }

            if(produtoDoBanco.getQtde() < qtdComprada){
                throw new IllegalArgumentException("Erro: Estoque insuficiente para o produto: " + produtoDoBanco.getDescricao());
            }

            int novoEstoque = produtoDoBanco.getQtde() - qtdComprada;
            produtoDoBanco.setQtde(novoEstoque);

            produtoDAO.atualizarProduto(produtoDoBanco);

        }

        vendaDAO.cadastrar(venda);
    }

    public void excluirVenda(Venda venda) {

        if (venda == null || venda.getId() <= 0) {
            throw new IllegalArgumentException("Erro: Venda inválida ou não selecionada!");
        }

        Venda vendaDoBanco = vendaDAO.buscarVendaPorId(venda.getId());

        if (vendaDoBanco == null) {
            throw new IllegalArgumentException("Erro: Venda não existe no banco de dados!");
        }

        for (ItemVenda item : vendaDoBanco.getItens()) {

            Produto produtoReal = produtoDAO.buscarProdutoPorId(item.getProduto().getId());

            if (produtoReal != null) {
                int qtdCancelada = item.getQuantidade();

                int estoqueDevolvido = produtoReal.getQtde() + qtdCancelada;

                produtoReal.setQtde(estoqueDevolvido);

                produtoDAO.atualizarProduto(produtoReal);
            }
        }

        vendaDAO.excluirVenda(venda.getId());
    }
}
