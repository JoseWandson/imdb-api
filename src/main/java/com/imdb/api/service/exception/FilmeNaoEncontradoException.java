package com.imdb.api.service.exception;

public class FilmeNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FilmeNaoEncontradoException(Long filmeId) {
		super(String.format("Não existe um cadastro de filme com id %d", filmeId));
	}

}
