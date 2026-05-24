package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private LocalDate dataVenda;
    private Cliente cliente;
    private List<ItemVenda> itens;

    public Venda(Cliente cliente) {
        this.dataVenda = LocalDate.now();
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public void adcionarItem(Produto produto, int quantidade){
        ItemVenda itemVenda = new ItemVenda(produto, quantidade);
        this.itens.add(itemVenda);
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
}