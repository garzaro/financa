package com.cleber.financa.model.entity;

import java.time.LocalDate;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name= "usuario", schema = "financeiro")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "nome_completo")
	private String nomeCompleto;
	
	@Column(name = "nome_usuario")
	private String nomeUsuario;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "senha")
	private String senha;
	
	@Convert(converter = Jsr310JpaConverters.LocalDateConverter.class)
	@Column(name = "data_cadastro")
	private LocalDate dataCadastro;
	
	/*getters and setters*/
	/*hashcode and equals*/
	/*toString*/

}
