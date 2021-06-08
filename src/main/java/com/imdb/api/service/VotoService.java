package com.imdb.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imdb.api.model.Voto;
import com.imdb.api.repository.VotoRepository;

@Service
public class VotoService {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private FilmeService filmeService;

	@Autowired
	private VotoRepository votoRepository;

	public void votar(Voto voto) {
		voto.setUsuario(usuarioService.buscarOuFalhar(voto.getUsuario().getId()));
		voto.setFilme(filmeService.buscarOuFalhar(voto.getFilme().getId()));
		votoRepository.save(voto);
	}

}
