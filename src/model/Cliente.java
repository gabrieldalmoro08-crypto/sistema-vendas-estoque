package model;

import java.time.LocalDate;

public class Cliente extends Usuario{

    public Cliente(String nome, String sobrenome, LocalDate dataNascimento, String senha) {
        super(nome, sobrenome, dataNascimento, senha);
    }

    public void cadastrar() throws Exception {
        super.realizarCadastro(18); // Regra para clientes
    }

}
