package com.imdb.api.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.imdb.api.model.Filme;
import com.imdb.api.model.Voto;
import com.imdb.api.repository.FilmeRepository;
import com.imdb.api.repository.filter.FilmeFilter;
import com.imdb.api.service.exception.FilmeNaoEncontradoException;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository filmeRepository;

	public Filme salvar(Filme filme) {
		return filmeRepository.save(filme);
	}

	public Filme buscarOuFalhar(Long id) {
		var filme = filmeRepository.findById(id).orElseThrow(() -> new FilmeNaoEncontradoException(id));
		filme.setMediaVotos(filme.getVotos().stream().collect(Collectors.averagingDouble(Voto::getNota)));
		return filme;
	}

	public Page<Filme> filtrar(FilmeFilter filmeFilter, Pageable pageable) {
		Page<Filme> filmes = filmeRepository.filtrar(filmeFilter, pageable);
		filmes.forEach(f -> f.setMediaVotos(f.getVotos().stream().collect(Collectors.averagingDouble(Voto::getNota))));
		return filmes;
	}

}
