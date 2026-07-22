package service;

import dao.*;
import model.*;

import java.util.List;

public class VendaService {

    private VendaDAO vendaDAO;
    private ProdutoDAO produtoDAO;

    public VendaService(VendaDAO vendaDAO, ProdutoDAO produtoDAO) {
        this.vendaDAO = vendaDAO;
        this.produtoDAO = produtoDAO;
    }

    public void realizarVenda(Venda venda) {

        if (venda == null || venda.getCliente() == null || venda.getCliente().getId() <= 0) {
            throw new IllegalArgumentException("Erro: Cliente não localizado!");
        }

        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            throw new IllegalArgumentException("Erro: A venda deve possuir ao menos um produto!");
        }

        venda.setDataVenda(java.time.LocalDate.now());

        double totalVenda = 0.0;

        for (ItemVenda item : venda.getItens()) {

            Produto produtoDaTela = item.getProduto();
            int qtdComprada = item.getQuantidade();

            Produto produtoDoBanco = produtoDAO.buscarProdutoPorId(produtoDaTela.getId());

            if (produtoDoBanco == null) {
                throw new IllegalArgumentException("Erro: Produto não encontrado!");
            }

            if (produtoDoBanco.getQtde() < qtdComprada) {
                throw new IllegalArgumentException("Erro: Estoque insuficiente para o produto: " + produtoDoBanco.getDescricao());
            }

            item.setPrecoUnitario(produtoDoBanco.getPreco());
            totalVenda += item.getPrecoUnitario() * qtdComprada;

            int novoEstoque = produtoDoBanco.getQtde() - qtdComprada;
            produtoDoBanco.setQtde(novoEstoque);

            produtoDAO.atualizarProduto(produtoDoBanco);
        }

        venda.setValorTotal(totalVenda);
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

    public Venda buscarVendaId(int id){

        if(id <= 0){
            throw new IllegalArgumentException("Erro: O ID da venda deve ser um número maior que zero!");
        }

        Venda vendaBanco = vendaDAO.buscarVendaPorId(id);

        if(vendaBanco == null){
            throw new IllegalArgumentException("Erro: Venda não encontrada no banco de dados!");
        }

      return vendaBanco;
    }

    public List<Venda> listarTodasVendas(){
        return vendaDAO.listarVendas();
    }
}
