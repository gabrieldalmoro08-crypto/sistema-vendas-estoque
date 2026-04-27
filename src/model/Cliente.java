package model;

import java.time.LocalDate;
import java.time.Period;

public class Cliente extends Usuario{

    public Cliente(String nome, String sobrenome, LocalDate dataNascimento, String senha) {
        super(nome, sobrenome, dataNascimento, senha);
    }


}
