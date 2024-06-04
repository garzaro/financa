package com.cleber.financa.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cleber.financa.exception.ErroDeValidacacao;
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
	public Usuario validarLogin(String email, String senha) {
		/*login*/
		Optional<Usuario> validandoLogin = usuarioRepository.findByEmail(email);
		/*verificar a existencia de usuario na base de dados*/
		if(!validandoLogin.isPresent()) {
			throw new ErroDeValidacacao("O email não foi encontrado");
		}
		if(!validandoLogin.get().getSenha().equals(senha)) {
			
		}
		return validandoLogin.get();
	}
	

	@Override
	public Usuario persistirUsuarioNabaseDeDados(Usuario usuario) {
		/*deve validar o email, se nao existir, persiste, (service)*/
		validarEmailNaBaseDedados(usuario.getEmail());
		
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
