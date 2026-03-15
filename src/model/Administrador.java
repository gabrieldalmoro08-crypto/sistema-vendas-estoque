package model;

import java.time.LocalDate;

public class Administrador extends Usuario {

    public Administrador(String nome, String sobrenome, LocalDate dataNascimento, String senha) {
        super(nome, sobrenome, dataNascimento, senha);
    }

    public void cadastrar() throws Exception {
        super.realizarCadastro(16); // Regra para estagiários
    }

}
