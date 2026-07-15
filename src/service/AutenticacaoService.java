package service;

import dao.UsuarioDAO;
import model.Usuario;

public class AutenticacaoService {

    private UsuarioDAO usuarioDAO;

    public AutenticacaoService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario fazerLogin(String cpf, String senha) {

        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O CPF não pode ficar em branco!");
        }

        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: A senha não pode ficar em branco!");
        }

        Usuario usuarioEncontrado = usuarioDAO.buscarUsuarioCompletoPorCPF(cpf);

        if (usuarioEncontrado == null) {
            throw new IllegalArgumentException("Erro: Usuário não encontrado! Verifique o CPF digitado.");
        }

        if (!usuarioEncontrado.getSenha().equals(senha)) {
            throw new IllegalArgumentException("Erro: Senha incorreta!");
        }

        return usuarioEncontrado;
    }
}