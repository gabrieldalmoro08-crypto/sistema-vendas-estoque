package service;

import dao.UsuarioDAO;
import model.Usuario;

public class UsuarioService {

    private UsuarioDAO usuarioDao = new UsuarioDAO();

    //
    public Usuario realizarLogin(String loginDigitado, String senhaDigitada) {

        Usuario usuarioEncontrado = usuarioDao.buscarPorNomeDigitado(loginDigitado);

        if (usuarioEncontrado == null){
            throw new RuntimeException("Aceso negado: Usuário não encontrado");
        }

        if (!usuarioEncontrado.getSenha().equals(senhaDigitada)){
            throw new RuntimeException("Acesso Negado: Senha incorreta.");
        }

        return usuarioEncontrado;
    }

}
