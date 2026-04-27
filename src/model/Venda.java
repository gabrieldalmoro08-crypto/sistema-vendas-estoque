package model;

public class Venda {

    private int id;
    private double preco;
    private int qtde;

    public Venda(int id, double preco, int qtde) {
        this.id = id;
        this.preco = preco;
        this.qtde = qtde;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }
}
