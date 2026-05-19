package model;

public class Produto {

    private int id;
    private double preco;
    private int qtde;
    private String descricao;


    public Produto(int id, double preco, int qtde, String descricao) {
        this.id = id;
        this.preco = preco;
        this.qtde = qtde;
        this.descricao = descricao;
    }

    public Produto() {
    }

    public boolean validacaoDescricao(String descricao){
        if (descricao == null){
            return false;
        }
        return true;
    }

    public boolean validacaoPreco(double preco){
        if (preco <= 0){
            return false;
        }
        return true;
    }

    public boolean validacaoQuantidade(int qtde){
        if (qtde <= 0){
            return false;
        }
        return true;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
