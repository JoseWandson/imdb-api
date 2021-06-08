package com.imdb.api.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imdb.api.dto.VotoDTO;
import com.imdb.api.model.Voto;
import com.imdb.api.service.VotoService;

@RestController
@RequestMapping("/votos")
public class VotoController {

	@Autowired
	private VotoService votoService;

	@Autowired
	private ModelMapper modelMapper;

	@PostMapping
	public void votar(@RequestBody @Valid VotoDTO votoDTO) {
		votoService.votar(modelMapper.map(votoDTO, Voto.class));
	}

}
