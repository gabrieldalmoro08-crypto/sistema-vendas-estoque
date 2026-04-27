package model;

import java.time.LocalDate;
import java.time.Period;

public abstract class Usuario {

    protected String nome;
    protected String sobrenome;
    protected LocalDate dataNascimento;
    protected String senha;

    public Usuario(String nome, String sobrenome, LocalDate dataNascimento, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.senha = senha;
    }

    public boolean verificarMaioridade(int idadeMinima) {
        if (this.dataNascimento == null)
            return false;
        int idadeCalculada = Period.between(this.dataNascimento, LocalDate.now()).getYears();
        return idadeCalculada >= idadeMinima;
    }

    public void validarCadastro (int idadeMinimaPermitida) {
        if (!verificarMaioridade(idadeMinimaPermitida)) {
            throw new RuntimeException("Acesso Negado: Idade mínima de " + idadeMinimaPermitida + " anos não atingida.");
        }
        System.out.println("Cadastro validado com sucesso para: " + this.nome);
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
