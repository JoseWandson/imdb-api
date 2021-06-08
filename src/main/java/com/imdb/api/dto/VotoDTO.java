package com.imdb.api.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class VotoDTO {

	@Min(0)
	@Max(4)
	private Integer nota;

	@NotNull
	private Long usuario;

	@NotNull
	private Long filme;

}
