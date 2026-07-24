package dao;

import model.*;
import util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ProdutoDAO {

    public void cadastrarProduto(Produto produto){

        String sql = "INSERT INTO produto (preco, qtde, descricao) VALUES (?, ?, ?)";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setDouble(1, produto.getPreco());
            stmt.setInt(2, produto.getQtde());
            stmt.setString(3, produto.getDescricao());

            stmt.execute();

        } catch(SQLException e) {
            throw new RuntimeException("Erro crítico ao salvar o produto no banco: " + e.getMessage(), e);
        }
    }

    public void excluirProduto(int id){

        String sql = "DELETE FROM produto WHERE id = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.executeUpdate();

        }catch(SQLException e) {
            throw new RuntimeException("Erro crítico ao deletar o produto no banco: " + e.getMessage(), e);
        }
    }

    public void atualizarProduto(Produto produto) {

        String sql = "UPDATE produto SET preco = ?, qtde = ?, descricao = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, produto.getPreco());
            stmt.setInt(2, produto.getQtde());
            stmt.setString(3, produto.getDescricao());
            stmt.setInt(4, produto.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro crítico ao atualizar o produto no banco: " + e.getMessage(), e);
        }
    }

    public List<Produto> listarTodosProdutos() {

            List<Produto> listaTodosProdutos = new ArrayList<>();

            String sql = "SELECT * FROM produto";

            try (Connection conn = ConnectionFactory.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()){

                 Produto produto = new Produto();

                 produto.setId(rs.getInt("id"));
                 produto.setPreco(rs.getDouble("preco"));
                 produto.setQtde(rs.getInt("qtde"));
                 produto.setDescricao(rs.getString("descricao"));

                 listaTodosProdutos.add(produto);
                }

            } catch (SQLException e) {
                throw new RuntimeException("Erro crítico ao listar os produtos: " + e.getMessage(), e);
            }
            return listaTodosProdutos;
        }

    public Produto buscarProdutoPorId(int id) {

        Produto produtoEncontrado = null;

        String sql = "SELECT * FROM produto WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1,id);

            try (ResultSet rs = stmt.executeQuery()){

                if(rs.next()){
                    produtoEncontrado = new Produto();
                    produtoEncontrado.setId(rs.getInt("id"));
                    produtoEncontrado.setPreco(rs.getDouble("preco"));
                    produtoEncontrado.setQtde(rs.getInt("qtde"));
                    produtoEncontrado.setDescricao(rs.getString("descricao"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro crítico ao buscar o id produto: " + e.getMessage(), e);
        }
        return produtoEncontrado;
    }

    public boolean verificarSeProdutoTemVenda(int idProduto) {
        String sql = "SELECT COUNT(*) FROM item_venda WHERE produto_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProduto);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int quantidadeDeVendas = rs.getInt(1);

                    if (quantidadeDeVendas > 0) {
                        return true;
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro interno no banco de dados ao verificar histórico: " + e.getMessage(), e);
        }

        return false;
    }

    public Produto buscarPorDescricao(String descricao) {
        String sql = "SELECT * FROM produto WHERE LOWER(descricao) = LOWER(?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, descricao.trim());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setQtde(rs.getInt("qtde"));
                    produto.setDescricao(rs.getString("descricao"));

                    return produto;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar produto por descrição: " + e.getMessage(), e);
        }

        return null;
    }

    public List<Produto> pesquisarPorParteDaDescricao(String termo) {
        List<Produto> listaResultados = new ArrayList<>();

        String sql = "SELECT * FROM produto WHERE LOWER(descricao) LIKE LOWER(?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + termo.trim() + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Produto produto = new Produto();
                    produto.setId(rs.getInt("id"));
                    produto.setPreco(rs.getDouble("preco"));
                    produto.setQtde(rs.getInt("qtde"));
                    produto.setDescricao(rs.getString("descricao"));

                    listaResultados.add(produto);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro crítico ao pesquisar produtos: " + e.getMessage(), e);
        }
        return listaResultados;
    }
}
