package dao;

import model.*;
import util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void excluirVenda(int id) {

        String sqlItens = "DELETE FROM item_venda WHARE venda_id = ?";
        String sqlVenda = "DELETE FROM venda WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmtItens = conn.prepareStatement(sqlItens);
             PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda)) {

            stmtItens.setInt(1, id);
            stmtItens.executeUpdate();

            stmtVenda.setInt(1, id);

            int linhasAfetadas = stmtVenda.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Sucesso: Venda deletada!");
            } else {
                System.out.println("Aviso: Nenhuma venda foi encontrada com o ID " + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro crítico ao deletar a venda no banco: " + e.getMessage(), e);
        }
    }

    public List<Venda> listarVendas(){

        List<Venda> listaVendas = new ArrayList<>();
        String sqlVenda = "SELECT * FROM venda";

        try(Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmtVenda = conn.prepareStatement(sqlVenda);
        ResultSet rsVenda = stmtVenda.executeQuery()){

            while (rsVenda.next()){
                Venda venda = new Venda();

                venda.setId(rsVenda.getInt("id"));
                venda.setDataVenda(rsVenda.getDate("data_venda").toLocalDate());

                Cliente cliente =  new Cliente();
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

    private List<ItemVenda> buscarItensPorVenda(int idVenda, Connection conn) throws SQLException{
        List<ItemVenda> listaItens = new ArrayList<>();
        String sqlItens = "SELECT * FROM item_venda WHERE venda_id = ?";

        try (PreparedStatement stmtItens = conn.prepareStatement(sqlItens)) {
            stmtItens.setInt(1, idVenda);

                try(ResultSet rsItens = stmtItens.executeQuery()){
                    ItemVenda item = new ItemVenda();

                    item.setQuantidade(rsItens.getInt("quantidade"));
                    item.setPrecoUnitario(rsItens.getDouble("preco_unitario"));

                    Produto produto = new Produto();
                    produto.setId(rsItens.getInt("produto_id"));
                    item.setProduto(produto);

                    listaItens.add(item);

                }
        }
        return listaItens;
    }
}