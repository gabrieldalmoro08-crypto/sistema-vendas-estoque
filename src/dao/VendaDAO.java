package dao;

import model.*;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    public void cadastrar(Venda venda) {

        String sqlVenda = "INSERT INTO venda (data_venda, valor_total, cliente_id) VALUES (?, ?, ?)";
        String sqlItem = "INSERT INTO item_venda (venda_id, produto_id, quantidade, preco_congelado) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda, new String[]{"id"})) {

            stmtVenda.setDate(1, java.sql.Date.valueOf(venda.getDataVenda()));
            stmtVenda.setDouble(2, venda.getValorTotal());
            stmtVenda.setInt(3, venda.getCliente().getId());

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
                    stmtItem.setDouble(4, item.getPrecoUnitario());

                    stmtItem.executeUpdate();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar a venda: " + e.getMessage(), e);
        }
    }

    public void excluirVenda(int id) {

        String sqlItens = "DELETE FROM item_venda WHERE venda_id = ?";
        String sqlVenda = "DELETE FROM venda WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmtItens = conn.prepareStatement(sqlItens);
             PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda)) {

            stmtItens.setInt(1, id);
            stmtItens.executeUpdate();

            stmtVenda.setInt(1, id);
            stmtVenda.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro crítico ao deletar a venda no banco: " + e.getMessage(), e);
        }
    }

    public List<Venda> listarVendas() {

        List<Venda> listaVendas = new ArrayList<>();
        String sqlVenda = "SELECT * FROM venda";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda);
             ResultSet rsVenda = stmtVenda.executeQuery()) {

            while (rsVenda.next()) {
                Venda venda = new Venda();

                venda.setId(rsVenda.getInt("id"));
                venda.setDataVenda(rsVenda.getDate("data_venda").toLocalDate());
                venda.setValorTotal(rsVenda.getDouble("valor_total"));

                Cliente cliente = new Cliente();
                cliente.setId(rsVenda.getInt("cliente_id"));
                venda.setCliente(cliente);

                List<ItemVenda> itens = buscarItensPorVenda(venda.getId(), conn);
                venda.setItens(itens);

                listaVendas.add(venda);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro crítico ao listar as vendas: " + e.getMessage(), e);
        }
        return listaVendas;
    }

    private List<ItemVenda> buscarItensPorVenda(int idVenda, Connection conn) throws SQLException {
        List<ItemVenda> listaItens = new ArrayList<>();
        String sqlItens = "SELECT * FROM item_venda WHERE venda_id = ?";

        try (PreparedStatement stmtItens = conn.prepareStatement(sqlItens)) {
            stmtItens.setInt(1, idVenda);

            try (ResultSet rsItens = stmtItens.executeQuery()) {
                while (rsItens.next()) {
                    ItemVenda item = new ItemVenda();

                    item.setQuantidade(rsItens.getInt("quantidade"));
                    item.setPrecoUnitario(rsItens.getDouble("preco_congelado"));

                    Produto produto = new Produto();
                    produto.setId(rsItens.getInt("produto_id"));
                    item.setProduto(produto);

                    listaItens.add(item);
                }
            }
        }
        return listaItens;
    }

    public Venda buscarVendaPorId(int id) {
        Venda venda = null;
        String sql = "SELECT * FROM venda WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    venda = new Venda();
                    venda.setId(rs.getInt("id"));
                    venda.setDataVenda(rs.getDate("data_venda").toLocalDate());
                    venda.setValorTotal(rs.getDouble("valor_total"));

                    Cliente cliente = new Cliente();
                    cliente.setId(rs.getInt("cliente_id"));
                    venda.setCliente(cliente);

                    List<ItemVenda> itens = buscarItensPorVenda(venda.getId(), conn);
                    venda.setItens(itens);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro crítico ao buscar venda por ID: " + e.getMessage(), e);
        }
        return venda;
    }
}