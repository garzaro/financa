package com.cleber.financa.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cleber.financa.exception.ErroDeValidacao;
import com.cleber.financa.exception.RegraDeNegocioException;
import com.cleber.financa.model.entity.Usuario;
import com.cleber.financa.model.repository.UsuarioRepository;
import com.cleber.financa.service.UsuarioService;

public class UsuarioServiceImpl implements UsuarioService{
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	@Override
	public Usuario autenticarUsuario(String email, String senha) {
		/*login*/ /**/
		Optional<Usuario> validandoLogin = usuarioRepository.findByEmail(email);
		/*verificar a existencia de usuario na base de dados*/
		if(!validandoLogin.isPresent()) {
			throw new ErroDeValidacao("Usuário não encontrado pelo email informado");
		}
		if(!validandoLogin.get().getSenha().equals(senha)) {
			throw new ErroDeValidacao("Senha inválida");
		}
		return validandoLogin.get();
	}
	
	@Override
	public Usuario persistirUsuarioNabaseDeDados(Usuario usuario) {
		/*deve validar o email, verificar se existe. (service)*/
		validarEmailNaBaseDedados(usuario.getEmail());
		/*se nao existir email, salvar a instancia*/
		return usuarioRepository.save(usuario);
	}

	@Override
	public void validarEmailNaBaseDedados(String email) {
		/*ver se o email existe*/
		boolean verificarSeOEmailExisteNaBAseDeDados = usuarioRepository.existsByEmail(email);
		if(verificarSeOEmailExisteNaBAseDeDados) {
			throw new RegraDeNegocioException("Já existe um usuario com esse email");
		}
		
	}

}
