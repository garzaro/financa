package com.cleber.financa.model.repository;

import java.time.LocalDate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.cleber.financa.model.entity.Usuario;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UsuarioRepositoryIntegrationTest {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	TestEntityManager testEntityManager;
	
	@Test
	public void testDeveVerificarAExistenciaDeUmEmailNaBaseDeDados() {
		/*cenario*/
		Usuario usuarioDeTeste = Usuario.builder()
				.nomeCompleto("Cleber Garzaro")
				.email("clebergarzaro74@gmail.com")
				.build();
		testEntityManager.persist(usuarioDeTeste);
				
		/*execução/ação*/
		boolean verificarSeExisteUmEmail = usuarioRepository
				.existsByEmail("clebergarzaro74@gmail.com");
		
		/*verificação*/
		Assertions.assertThat(verificarSeExisteUmEmail).isTrue();
						
	}
	
	@Test
	public void testRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		/*cenario, não deve existir email na base*/
		/*execução/ação*/
		boolean verificarSeExisteUsuarioCadastradoComEmail = usuarioRepository
				.existsByEmail("clebergarzaro74@gmail,com");
		
		/*verificação*/
		Assertions.assertThat(verificarSeExisteUsuarioCadastradoComEmail).isFalse();
		
	}
	
	@Test
	public void testDeveRecuperarUmaInstanciaDeUsuarioPeloId() {
		/*cenario, retornar um usuario cadastrado pelo id*/
		Usuario criarUmaInstanciaDeUsuarioERecuperarPeloId = Usuario.builder()
				.nomeCompleto("Cleber Garzaro")
				.nomeUsuario("garzaro")
				.email("clebergarzaro74@gmail.com")
				.senha("123456")
				.dataCadastro(LocalDate.now())
				.build();
		testEntityManager.persist(criarUmaInstanciaDeUsuarioERecuperarPeloId);
		/*verificação*/
		Usuario recuperarAInstanciaCriada = usuarioRepository
				.findById(criarUmaInstanciaDeUsuarioERecuperarPeloId.getId())
				.orElseThrow();
		
		Assertions.assertThat(recuperarAInstanciaCriada).isNotNull();
		Assertions.assertThat(recuperarAInstanciaCriada.getNomeCompleto()).isEqualTo("Cleber Garzaro");
		Assertions.assertThat(recuperarAInstanciaCriada.getNomeUsuario()).isEqualTo("garzaro");
		Assertions.assertThat(recuperarAInstanciaCriada.getEmail()).isEqualTo("clebergarzaro74@gmail.com");
		Assertions.assertThat(recuperarAInstanciaCriada.getSenha()).isEqualTo("123456");
		Assertions.assertThat(recuperarAInstanciaCriada.getDataCadastro()).isEqualTo(LocalDate.now());
			
	}
	
}
