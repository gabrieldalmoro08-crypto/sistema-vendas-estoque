package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {

    private int id;
    private LocalDate dataVenda;
    private Cliente cliente;
    private List<ItemVenda> itens;
    private double valorTotal;

    public Venda(Cliente cliente) {
        this.dataVenda = LocalDate.now();
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public Venda() {
    }

    public void adcionarItem(Produto produto, int quantidade){
        ItemVenda itemVenda = new ItemVenda(produto, quantidade);
        this.itens.add(itemVenda);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}