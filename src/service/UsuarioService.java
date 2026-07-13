package service;

import dao.*;
import model.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class UsuarioService {

    private UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void cadastrarUsuario(Usuario usuario){

        validarDadosBasicos(usuario);

        boolean cpfJaCadastrado = usuarioDAO.buscaUsuarioPorCPF(usuario.getCPF());

        if(cpfJaCadastrado){
            throw new IllegalArgumentException("Erro: CPF já cadastrado no sistema!");
        }

        usuarioDAO.cadastrar(usuario);

    }

    public void excluirUsuario (Usuario usuario){

        if(usuario == null){
            throw new IllegalArgumentException("Erro: Os dados não podem estar vazios!");
        }

        if(usuario.getId() <= 0){
            throw new IllegalArgumentException("Erro: ID de usuário inválido para exclusão!");
        }
        Usuario usuarioBanco = usuarioDAO.buscarUsuarioPorId(usuario.getId());

        if(usuarioBanco == null){
            throw new IllegalArgumentException("Erro: O ID informado não existe no banco de dados!");
        }

        usuarioDAO.excluirUsuario(usuario.getId());

    }

    public void atualizarUsuario(Usuario usuario){

        if(usuario == null){
            throw new IllegalArgumentException("Erro: Os dados não podem estar vazio!");
        }

        if(usuario.getId() <= 0){
            throw new IllegalArgumentException("Erro: ID de usuário inválido para atualização!");
        }

        Usuario encontradoUsuario = usuarioDAO.buscarUsuarioPorId(usuario.getId());

        if(encontradoUsuario == null){
            throw new IllegalArgumentException("Erro: O ID informado não existe no banco de dados");
        }

        validarDadosBasicos(usuario);


        if (!encontradoUsuario.getCPF().equals(usuario.getCPF())) {
            if (usuarioDAO.buscaUsuarioPorCPF(usuario.getCPF())) {
                throw new IllegalArgumentException("Erro: Este novo CPF já está em uso por outro usuário!");
            }
        }

        usuarioDAO.atualizarCadastro(usuario);

    }

    private void validarDadosBasicos(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Erro: Os dados não podem estar vazios!");
        }
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O nome do usuário não pode ficar em branco!");
        }
        if (usuario.getSobrenome() == null || usuario.getSobrenome().trim().isEmpty()) {
            throw new IllegalArgumentException("Erro: O sobrenome do usuário não pode ficar em branco!");
        }
        if (usuario.getDataNascimento() == null) {
            throw new IllegalArgumentException("Erro: A data de nascimento é obrigatória!");
        }
        if (usuario.getDataNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Erro: A data de nascimento não pode estar no futuro!");
        }

        int idade = Period.between(usuario.getDataNascimento(), LocalDate.now()).getYears();

        if (idade > 120) {
            throw new IllegalArgumentException("Erro: Data de nascimento inválida! Verifique o ano digitado.");
        }
        if (idade < 18) {
            throw new IllegalArgumentException("Erro: Usuário deve ter no mínimo 18 anos!");
        }
    }

    public Usuario usuarioBuscarPorID(int id){

        if(id <= 0){
            throw new IllegalArgumentException("Erro: ID inválido para busca!");
        }
       return usuarioDAO.buscarUsuarioPorId(id);
    }


    public List<Usuario> listarTodosUsuarios(){
        return usuarioDAO.listarUsuarios();
    }

    public Usuario usuarioBuscarPorCPF(String cpf){
        if(cpf == null || cpf.trim().isEmpty()){
            throw new IllegalArgumentException("Erro: O CPF não pode ser vázio!");
        }
        return usuarioDAO.buscarUsuarioCompletoPorCPF(cpf);
    }
}
