package com.cleber.financa.service;

import com.cleber.financa.model.entity.Usuario;

public interface UsuarioService {
	
	/*verificar se o usuario existe na base, validação*/
	Usuario autenticarUsuario(String email, String senha); 
	
	/*salvar o usuario na base*/
	Usuario persistirUsuarioNabaseDeDados(Usuario usuario);
	
	/*verifica o email na base de dados, unique */
	void validarEmailNaBaseDedados(String email);

}
