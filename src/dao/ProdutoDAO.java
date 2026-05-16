package dao;

import model.*;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
