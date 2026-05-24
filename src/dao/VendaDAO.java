package dao;

import model.*;
import util.ConnectionFactory;

import java.sql.*;

public class VendaDAO {

    public void cadastrar(Venda venda) {

        String sqlVenda = "INSERT INTO venda (data_venda, id_cliente) VALUES (?, ?)";

        String sqlItem = "INSERT INTO item_venda (id_venda, id_produto, quantidade) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, Statement.RETURN_GENERATED_KEYS)) {

            stmtVenda.setDate(1, java.sql.Date.valueOf(venda.getDataVenda()));
            stmtVenda.setInt(2, venda.getCliente().getId());

            stmtVenda.executeUpdate();

            ResultSet rs = stmtVenda.getGeneratedKeys();
            int idVendaGerado = 0;

            if (rs.next()) {
                idVendaGerado = rs.getInt(1);
            }


            try (PreparedStatement stmtItem = conn.prepareStatement(sqlItem)) {


                for (ItemVenda item : venda.getItens()) {

                    stmtItem.setInt(1, idVendaGerado);
                    stmtItem.setInt(2, item.getProduto().getId());
                    stmtItem.setInt(3, item.getQuantidade());

                    stmtItem.executeUpdate();
                }
            }

            System.out.println("Sucesso: Venda e itens cadastrados perfeitamente!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar a venda: " + e.getMessage(), e);
        }
    }
}
