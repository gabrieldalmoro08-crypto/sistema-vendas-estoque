package model;

public class Produto {

    private double preco;
    private int qtde;

    public Produto(double preco, int qtde) {
        this.preco = preco;
        this.qtde = qtde;
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
