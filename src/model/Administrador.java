package model;

import java.time.LocalDate;
import java.time.Period;

public class Administrador extends Usuario {

    private static final int IDADE_MINIMA = 18;

    public Administrador(int id, String nome, String sobrenome, LocalDate dataNascimento, String senha, String CPF) {
        super(id, nome, sobrenome, dataNascimento, senha, CPF);
    }

    public Administrador() {
    }
}
