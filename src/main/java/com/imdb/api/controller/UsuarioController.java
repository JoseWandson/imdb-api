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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.imdb.api.dto.UsuarioDTO;
import com.imdb.api.event.RecursoCriadoEvent;
import com.imdb.api.model.Usuario;
import com.imdb.api.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario adicionar(@RequestBody @Valid UsuarioDTO usuarioDTO, HttpServletResponse response) {
		var usuarioSalvo = usuarioService.salvar(modelMapper.map(usuarioDTO, Usuario.class), false);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getId()));

		return usuarioSalvo;
	}

	@PostMapping("/administrador")
	@ResponseStatus(HttpStatus.CREATED)
	public Usuario adicionarAdministrador(@RequestBody @Valid UsuarioDTO usuarioDTO, HttpServletResponse response) {
		var usuarioSalvo = usuarioService.salvar(modelMapper.map(usuarioDTO, Usuario.class), true);

		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getId()));

		return usuarioSalvo;
	}

	@PutMapping("/{id}")
	public Usuario atualizar(@PathVariable Long id, @RequestBody @Valid UsuarioDTO usuarioDTO) {
		var usuario = modelMapper.map(usuarioDTO, Usuario.class);
		return usuarioService.atualizar(id, usuario);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		usuarioService.remover(id);
	}

	@GetMapping
	public Page<Usuario> listar(@PageableDefault(sort = "nome") Pageable pageable) {
		return usuarioService.listar(pageable);
	}

}
