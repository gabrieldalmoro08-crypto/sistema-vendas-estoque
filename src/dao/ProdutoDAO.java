package dao;

import model.*;
import util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
            System.out.println("Sucesso: Produto " + produto.getDescricao() + " foi salvo no banco de dados!");

        } catch(SQLException e) {
            throw new RuntimeException("Erro crítico ao salvar o produto no banco: " + e.getMessage(), e);
        }
    }

    public void excluirProduto(int id){

        String sql = "DELETE FROM produto WHERE id = ?";

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

        stmt.setInt(1, id);

        int linhasAfetadas = stmt.executeUpdate();

        if (linhasAfetadas > 0){
            System.out.println("Sucesso: Produto deletado!");
        } else{
            System.out.println("Aviso: Nenhum produto encontrado com o ID " + id);
        }

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

            int linhaAfetada = stmt.executeUpdate();

            if (linhaAfetada > 0) {
                System.out.println("Sucesso: Produto atualizado com sucesso!");
            } else {
                System.out.println("Aviso: Nenhum produto encontrado com o ID " + produto.getId());
            }

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


}
