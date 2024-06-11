package com.cleber.financa.exception;

public class ErroDeValidacacao extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ErroDeValidacacao(String messageErrorValidacao) {
		super(messageErrorValidacao);
		
	}

}
