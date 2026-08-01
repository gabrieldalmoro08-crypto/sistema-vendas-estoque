package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String URL = "jdbc:postgresql://localhost:5433/sistema_BD";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (SQLException e) {
            e.printStackTrace(); // ISSO AQUI VAI MOSTRAR O MOTIVO REAL EM VERMELHO!
            throw new RuntimeException("Erro ao conectar com o banco de dados!", e);
        }
    }
}