package dao;

import model.Usuario;
import model.Cliente;
import model.Administrador;
import util.ConnectionFactory;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDAO {

    // Método para salvar um novo usuário no banco de dados
    public void cadastrar(Usuario usuario) {

        String sql = "INSERT INTO usuario (nome, sobrenome, data_nascimento, senha, cpf, tipo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());

            stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));

            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getCPF());

            // 4. A Lógica do Tipo: Como seu Java não tem o atributo 'tipo',
            // descobrimos quem ele é usando o 'instanceof'
            if (usuario instanceof Administrador) {
                stmt.setString(6, "ADMINISTRADOR");
            } else if (usuario instanceof Cliente) {
                stmt.setString(6, "CLIENTE");
            }

            // 5. Manda executar a inserção no banco de dados!
            stmt.execute();
            System.out.println("Sucesso: Usuário " + usuario.getNome() + " foi salvo no banco de dados!");

        } catch (SQLException e) {
            // Se der erro de conexão, senha ou banco desligado, ele avisa aqui
            throw new RuntimeException("Erro crítico ao salvar o usuário no banco: " + e.getMessage(), e);
        }
    }
}