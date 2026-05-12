package model;

import java.time.LocalDate;
import java.time.Period;

public class Cliente extends Usuario{

    public Cliente(int id, String nome, String sobrenome, LocalDate dataNascimento, String senha, String CPF) {
        super(id, nome, sobrenome, dataNascimento, senha, CPF);
    }
}
