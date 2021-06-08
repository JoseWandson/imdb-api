package com.imdb.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.imdb.api.model.Perfil;
import com.imdb.api.model.Permissao;
import com.imdb.api.model.Usuario;
import com.imdb.api.model.Usuario_;
import com.imdb.api.repository.UsuarioRepository;
import com.imdb.api.security.AppUserDetailsService;
import com.imdb.api.service.exception.UsuarioNaoEncontradoException;
import com.imdb.api.service.exception.UsuarioSemPermissaoException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AppUserDetailsService appUserDetailsService;

	public Usuario salvar(Usuario usuario, boolean isAdministrador) {
		List<Permissao> permissoes = new ArrayList<>();
		permissoes.add(new Permissao(Perfil.ROLE_USUARIO));
		if (isAdministrador) {
			permissoes.add(new Permissao(Perfil.ROLE_ADMINISTRADOR));
		}
		usuario.setPermissoes(permissoes);
		return usuarioRepository.save(usuario);
	}

	public Usuario buscarOuFalhar(Long usuarioId) {
		return usuarioRepository.findByIdAndAtivoTrue(usuarioId)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}

	public Usuario atualizar(Long id, Usuario usuario) {
		var usuarioSalvo = buscarOuFalhar(id);
		if (Perfil.naoTemPermissao(appUserDetailsService.getUsuario().getPermissoes(), usuarioSalvo.getPermissoes())) {
			throw new UsuarioSemPermissaoException();
		}

		BeanUtils.copyProperties(usuario, usuarioSalvo, Usuario_.id.getName());

		return usuarioRepository.save(usuario);
	}

	public void remover(Long id) {
		var usuario = buscarOuFalhar(id);
		if (Perfil.naoTemPermissao(appUserDetailsService.getUsuario().getPermissoes(), usuario.getPermissoes())) {
			throw new UsuarioSemPermissaoException();
		}

		usuario.setAtivo(false);
		usuarioRepository.save(usuario);
	}

	public Page<Usuario> listar(Pageable pageable) {
		return usuarioRepository.findAll(pageable);
	}

}
