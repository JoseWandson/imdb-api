package com.imdb.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

	@NotBlank
	private String nome;

	@NotBlank
	private String email;

	@NotBlank
	private String senha;

}
