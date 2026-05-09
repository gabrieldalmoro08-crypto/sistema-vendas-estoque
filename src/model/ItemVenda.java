package model;

public class ItemVenda {

    private Produto produto;
    private int quantidade;
    private double precoUnitario; // Preço no momento da venda

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = produto.getPreco(); //Momento que congela o preço atual do produto na hora da venda
    }

    public double subTotal(){
        return this.quantidade * this.precoUnitario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
