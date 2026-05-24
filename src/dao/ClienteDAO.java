package dao;

import model.Cliente;
import util.ConnectionFactory;
import java.time.LocalDate;
import java.time.Period;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClienteDAO {

    public void cadastrarCliente(Cliente cliente){

        String sql = "INSERT INTO cliente (nome, sobrenome, dataNascimento, senha, CPF) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getSobrenome());
            stmt.setDate(3, java.sql.Date.valueOf(cliente.getDataNascimento()));
            stmt.setString(4, cliente.getSenha());
            stmt.setString(5, cliente.getCPF());

            stmt.execute();
            System.out.println("Sucesso: Cliente " + cliente.getNome() + " foi salvo no banco de dados!");

        } catch(SQLException e) {
            throw new RuntimeException("Erro crítico ao salvar o cliente no banco: " + e.getMessage(), e);
        }
    }

    public void excluirCliente1(int id){

        String sql = "DELETE FROM cliente WHERE id = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0){
                System.out.println("Sucesso: Cliente deletado!");
            } else{
                System.out.println("Aviso: Nenhum cliente encontrado com o ID " + id);
            }

        }catch(SQLException e) {
            throw new RuntimeException("Erro crítico ao deletar o cliente no banco: " + e.getMessage(), e);
        }
    }
}
