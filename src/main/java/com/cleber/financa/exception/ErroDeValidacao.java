package com.cleber.financa.exception;

public class ErroDeValidacao extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ErroDeValidacao(String messageErrorValidacao) {
		super(messageErrorValidacao);
		
	}

}
