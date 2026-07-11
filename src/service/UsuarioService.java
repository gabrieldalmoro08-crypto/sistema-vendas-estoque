package service;

import dao.*;
import model.*;

import java.time.LocalDate;
import java.time.Period;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void cadastrarUsuario(Usuario usuario){

        if(usuario == null){
            throw new IllegalArgumentException("Erro: Os dados não podem estar vazio!");
        }

        if(usuario.getNome() == null || usuario.getNome().trim().isEmpty()){
            throw new IllegalArgumentException("Erro: O nome do usuário não pode ficar em branco!");
        }

        if(usuario.getSobrenome() == null || usuario.getSobrenome().trim().isEmpty()){
            throw new IllegalArgumentException("Erro: O sobrenome do usuário não pode ficar em branco!");
        }

        if(usuario.getDataNascimento() == null){
            throw new IllegalArgumentException("Erro: A data de nascimento é obrigatória!");
        }

        if(usuario.getDataNascimento().isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Erro: A data de nascimento não pode estar no futuro!");
        }

        int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();


        if (idade > 120) {
        throw new IllegalArgumentException("Erro: Data de nascimento inválida! Verifique o ano digitado.");
        }

        if(idade < 18){
            throw new IllegalArgumentException("Erro: Usuário deve ter no mínimo 18 anos para se cadastrar!");
        }

        boolean cpfJaCadastrado = usuarioDAO.buscaUsuarioPorCPF(usuario.getCPF());

        if(cpfJaCadastrado){
            throw new IllegalArgumentException("Erro: CPF já cadastrado no sistema!");
        }

        usuarioDAO.cadastrar(usuario);

    }
}
