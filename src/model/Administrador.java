package model;

import java.time.LocalDate;
import java.time.Period;

public class Administrador extends Usuario {

    private static final int IDADE_MINIMA = 18;

    public Administrador(String nome, String sobrenome, LocalDate dataNascimento, String senha) {
        super(nome, sobrenome, dataNascimento, senha);
    }

}
