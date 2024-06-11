package com.cleber.financa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cleber.financa.exception.RegraDeNegocioException;
import com.cleber.financa.model.entity.Usuario;
import com.cleber.financa.model.repository.UsuarioRepository;
import com.cleber.financa.service.impl.UsuarioServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceUnitTest {
	
	/*não é necessario injetar, usando o mock*/
	UsuarioService usuarioService;
	
	UsuarioRepository usuarioRepository;
	
	/*metodo configurado; configuração ou inicialização*/
	public void setUp() {
		/*criando instancia fake*/
		usuarioRepository = Mockito.mock(UsuarioRepository.class);
		/*criando instancia real do usuario service*/
		usuarioService = new UsuarioServiceImpl(usuarioRepository);
		
	}
	
	@Test(expected = Test.None.class)
	public void testServiceDeveValidarEmail() {
		/*cenario*/
		usuarioRepository.deleteAll();
		
		/*ação*/
		usuarioService.validarEmailNaBaseDedados("clebergarzaro74@gmail.com");
	}
	
	@Test(expected = RegraDeNegocioException.class)
	public void testServiceDevelancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		/*cenario*/
		Usuario jaExisteEmailCadastrado = Usuario.builder()
				.nomeUsuario("garzaro74")
				.email("clebergarzaro74@gmail.com")
				.build();
		usuarioRepository.save(jaExisteEmailCadastrado);
		
		/*ação*/
		usuarioService.validarEmailNaBaseDedados("clebergarzaro74@gmail.com");
		
	}
	
}
