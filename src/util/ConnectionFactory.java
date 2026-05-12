package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    // O "endereço" do seu banco de dados
    private static final String URL = "jdbc:postgresql://localhost:5432/sistema_BD";
    private static final String USUARIO = "postgres"; //
    private static final String SENHA = "postgres"; //

    // Método que os seus DAOs vão chamar para pegar a conexão
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            // Se der erro (ex: banco desligado, senha errada), ele avisa aqui
            throw new RuntimeException("Erro ao conectar com o banco de dados!", e);
        }
    }
}