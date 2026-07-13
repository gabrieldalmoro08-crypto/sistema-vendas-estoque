package dao;

import model.Produto;
import model.Usuario;
import model.Cliente;
import model.Administrador;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public void cadastrar(Usuario usuario) {

        String sql = "INSERT INTO usuario (nome, sobrenome, data_nascimento, senha, cpf, tipo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());

            stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));

            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getCPF());

            if (usuario instanceof Administrador) {
                stmt.setString(6, "ADMINISTRADOR");
            } else if (usuario instanceof Cliente) {
                stmt.setString(6, "CLIENTE");
            }

            stmt.executeUpdate();
            System.out.println("Sucesso: Usuário " + usuario.getNome() + " foi salvo no banco de dados!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro crítico ao salvar o usuário no banco: " + e.getMessage(), e);
        }
    }

    public void excluirUsuario(int id) {

        String sql = "DELETE FROM usuario WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Sucesso: Cliente ou Administrador deletado!");
            } else {
                System.out.println("Aviso: Nenhum cliente ou admnistrador encontrado com o ID " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro crítico ao deletar no banco: " + e.getMessage(), e);
        }
    }

    public List<Usuario> listarUsuarios() {

        List<Usuario> listarUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuario";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Usuario usuario;
                String tipo = rs.getString("Tipo");

                if ("ADMINISTRADOR".equals(tipo)) {
                    usuario = new Administrador();
                } else {
                    usuario = new Cliente();
                }

                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSobrenome(rs.getString("sobrenome"));

                // <<< A MÁGICA ACONTECE AQUI TAMBÉM: Para a lista não vir sem CPF e Senha!
                usuario.setCPF(rs.getString("cpf"));
                usuario.setSenha(rs.getString("senha"));

                if (rs.getDate("data_nascimento") != null) {
                    usuario.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                }

                listarUsuarios.add(usuario);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro crítico ao listar os usuarios: " + e.getMessage(), e);
        }
        return listarUsuarios;
    }

    public Usuario buscarUsuarioPorId(int id) {

        Usuario usuarioEncontrado = null;
        String sql = "SELECT * FROM usuario WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    String tipo = rs.getString("Tipo");

                    if ("ADMINISTRADOR".equals(tipo)) {
                        usuarioEncontrado = new Administrador();
                    } else {
                        usuarioEncontrado = new Cliente();
                    }

                    usuarioEncontrado.setId(rs.getInt("id"));
                    usuarioEncontrado.setNome(rs.getString("nome"));
                    usuarioEncontrado.setSobrenome(rs.getString("sobrenome"));

                    // <<< A MÁGICA ACONTECE AQUI: Pegando o CPF e a Senha do banco!
                    usuarioEncontrado.setCPF(rs.getString("cpf"));
                    usuarioEncontrado.setSenha(rs.getString("senha"));

                    if (rs.getDate("data_nascimento") != null) {
                        usuarioEncontrado.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro crítico ao buscar o id do usuario: " + e.getMessage(), e);
        }
        return usuarioEncontrado;
    }

    public void atualizarCadastro(Usuario usuario) {

        String sql = "UPDATE usuario SET nome = ?, sobrenome = ?, data_nascimento = ?, senha = ?, cpf = ?, tipo = ? WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,usuario.getNome());
            stmt.setString(2, usuario.getSobrenome());
            stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(4, usuario.getSenha());
            stmt.setString(5, usuario.getCPF());

            if(usuario instanceof Administrador){
                stmt.setString(6, "ADMINISTRADOR");
            } else if (usuario instanceof Cliente){
                stmt.setString(6, "CLIENTE");
            }

            stmt.setInt(7, usuario.getId());
            stmt.executeUpdate();

            System.out.println("Sucesso: Usuário " + usuario.getNome() + " foi atualizado no banco de dados!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro crítico ao atualizar o usuário no banco: " + e.getMessage(), e);
        }
    }

    public boolean buscaUsuarioPorCPF(String cpf){

        String sql = "SELECT * FROM usuario WHERE cpf = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1,cpf);

            try(ResultSet rs = stmt.executeQuery()){

                if(rs.next()){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro crítico ao verificar CPF no banco: " + e.getMessage(), e);
        } return false;
    }

    public Usuario buscarUsuarioCompletoPorCPF(String cpf){

        Usuario usuarioEncontrado = null;
        String sql = "SELECT * FROM usuario WHERE cpf = ?";

        try(Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cpf);

            try(ResultSet rs = stmt.executeQuery()){

                if(rs.next()){

                    String tipo = rs.getString("Tipo");

                    if("ADMINISTRADOR".equals(tipo)){
                        usuarioEncontrado = new Administrador();
                    } else {
                        usuarioEncontrado = new Cliente();
                    }

                    usuarioEncontrado.setId(rs.getInt("id"));
                    usuarioEncontrado.setNome(rs.getString("nome"));
                    usuarioEncontrado.setSobrenome(rs.getString("sobrenome"));
                    usuarioEncontrado.setCPF(rs.getString("cpf"));
                    usuarioEncontrado.setSenha(rs.getString("senha"));

                    if(rs.getDate("data_nascimento") != null){
                        usuarioEncontrado.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());                    }
                }
            }
        } catch (SQLException e){
            throw new RuntimeException("Erro crítico ao buscar o usuário por CPF: " + e.getMessage(), e);
        } return usuarioEncontrado;
    }

}