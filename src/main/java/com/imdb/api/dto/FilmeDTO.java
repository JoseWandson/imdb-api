package com.imdb.api.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilmeDTO {

	@NotBlank
	private String nome;

	@NotBlank
	private String diretor;

	@NotBlank
	private String genero;

	@NotEmpty
	private List<String> atores;

}
