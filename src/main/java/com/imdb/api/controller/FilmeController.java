package com.imdb.api.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.imdb.api.dto.FilmeDTO;
import com.imdb.api.event.RecursoCriadoEvent;
import com.imdb.api.model.Filme;
import com.imdb.api.repository.filter.FilmeFilter;
import com.imdb.api.service.FilmeService;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

	@Autowired
	private FilmeService filmeService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Filme adicionar(@RequestBody @Valid FilmeDTO filmeDTO, HttpServletResponse response) {
		var filmeSalvo = filmeService.salvar(modelMapper.map(filmeDTO, Filme.class));

		publisher.publishEvent(new RecursoCriadoEvent(this, response, filmeSalvo.getId()));

		return filmeSalvo;
	}

	@GetMapping
	public Page<Filme> filtrar(FilmeFilter filmeFilter, @PageableDefault(sort = "nome") Pageable pageable) {
		return filmeService.filtrar(filmeFilter, pageable);
	}

	@GetMapping("/{id}")
	public Filme buscar(@PathVariable Long id) {
		return filmeService.buscarOuFalhar(id);
	}

}
