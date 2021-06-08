package com.imdb.api.service.exception;

public class UsuarioNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(Long usuarioId) {
		super(String.format("Não existe um cadastro de usuário com id %d", usuarioId));
	}

}
