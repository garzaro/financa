package com.cleber.financa.model.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.cleber.financa.model.entity.Usuario;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@ActiveProfiles("test")
public class UsuarioRepositoryIntegrationTest {
	
	/*Inicio teste com Dougllas Sousa (Udemy)*/
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	//@Autowired
	//TestEntityManager testEntityManager;
	
	@Test
	@DisplayName("o email existe na base de dados")
	public void testDeveVerificarAExistenciaDeUmEmailNaBaseDeDados() {
		/*cenario*/
		Usuario usuarioDeTeste = Usuario.builder()
				.nomeCompleto("Cleber Garzaro")
				.email("clebergarzaro74@gmail.com")
				.build();
		usuarioRepository.save(usuarioDeTeste);
		//testEntityManager.persist(usuarioDeTeste);
				
		/*execução/ação*/
		boolean verificarSeExisteUmEmail = usuarioRepository.existsByEmail("clebergarzaro74@gmail.com");
		
		/*verificação*/
		Assertions.assertThat(verificarSeExisteUmEmail).isTrue();
						
	}
	
	@Test
	@DisplayName("falso, nao ha cadastro com o email")
	public void testRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		/*cenario, não deve existir email na base*/
		/*execução/ação*/
		boolean verificarSeExisteUsuarioCadastradoComEmail = usuarioRepository
				.existsByEmail("clebergarzaro74@gmail,com");
		
		/*verificação*/
		Assertions.assertThat(verificarSeExisteUsuarioCadastradoComEmail).isFalse();
		
	}
	
	@Test
	@DisplayName("Usuario salvo na base")
	public void testDevePersistirUmUsuarioNaBaseDeDados() {
		/*cenario*/
		Usuario persistindoUsuario = Usuario.builder()
				.nomeCompleto("Cleber Garzaro")
				.nomeUsuario("garzaro")
				.email("clebergarzaro74@gmail.com")
				.senha("senha")
				.dataCadastro(LocalDate.now())
				.build();
		/*ação*/
		Usuario usuarioPersistido = usuarioRepository.save(persistindoUsuario);
		
		/*verificação*/
		Assertions.assertThat(usuarioPersistido.getId()).isNotNull();
	}
	
	@Test
	@DisplayName("Usuario buscado pelo email")
	public void testDeveBuscarUmUsuarioPeloEmail() {
		/*cenario*/
		Usuario salvandoUsuario = Usuario.builder()
				.nomeCompleto("Cleber Garzaro")
				.email("usuario@gmail.com")
				.dataCriacao(LocalDateTime.now())
				.build();
		usuarioRepository.save(salvandoUsuario);
		
		/*ação*/
	    Optional<Usuario> resultadoUsuarioSalvo = usuarioRepository.findByEmail("usuario@gmail.com");
		
		/*verificação, verifica a presenca do email*/
		Assertions.assertThat(resultadoUsuarioSalvo.isPresent()).isTrue();
	}
	
	@Test
	@DisplayName("Retornou vazio consulta por email que nao esta na base")
	public void testRetornarVazioAoBuscarUsuarioPorEmailQueNaoExisteNaBase() {
		/*presença*/
		usuarioRepository.deleteAll();
		/*ação*/
		Optional<Usuario> usuarioInexistente = usuarioRepository.findByEmail("clebergarzaro74@gmail.com");
		/*verificação*/
		Assertions.assertThat(usuarioInexistente.isPresent()).isFalse();
		
	}
		
	/*IA*/
	@Test
	@DisplayName("Instancia recuperada por id")
	public void testDeveRecuperarUmaInstanciaDeUsuarioPeloId() {
		/*cenario, retornar um usuario cadastrado pelo id*/
		Usuario criarUmaInstanciaDeUsuarioERecuperarPeloId = Usuario.builder()
				.nomeCompleto("Cleber Garzaro")
				.nomeUsuario("énoiz")
				.email("clebergarzaro74@gmail.com")
				.senha("s3nh4")
				.dataCadastro(LocalDate.now())
				.dataCriacao(LocalDateTime.now())
				.build();
		usuarioRepository.save(criarUmaInstanciaDeUsuarioERecuperarPeloId);
		//testEntityManager.persist(criarUmaInstanciaDeUsuarioERecuperarPeloId);
		/*verificação*/
		Usuario recuperarAInstanciaCriada = usuarioRepository
				.findById(criarUmaInstanciaDeUsuarioERecuperarPeloId.getId())
				.orElseThrow();
		
		Assertions.assertThat(recuperarAInstanciaCriada).isNotNull();
		/*comparar*/
		Assertions.assertThat(recuperarAInstanciaCriada.getNomeCompleto()).isEqualTo("Cleber Garzaro");
		Assertions.assertThat(recuperarAInstanciaCriada.getNomeUsuario()).isEqualTo("énoiz");
		Assertions.assertThat(recuperarAInstanciaCriada.getEmail()).isEqualTo("clebergarzaro74@gmail.com");
		Assertions.assertThat(recuperarAInstanciaCriada.getSenha()).isEqualTo("s3nh4");
		Assertions.assertThat(recuperarAInstanciaCriada.getDataCadastro()).isEqualTo(LocalDate.now());
			
	}
	
	/*para nao ter que ficar digitando as linhas de criação de instancia
	 * de usuario em todos os metodos*/
	public static Usuario criarUsuario() {
		return Usuario.builder()
				.nomeCompleto("Cleber Garzaro")
				.nomeUsuario("garzaro")
				.email("clebergarzaro74@gmail.com")
				.senha("s3nh4")
				.dataCadastro(LocalDate.now())
				.build();
	}
	
}
