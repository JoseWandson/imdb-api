package com.imdb.api.model;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Perfil {

	ROLE_ADMINISTRADOR(1), ROLE_USUARIO(2);

	private final Integer id;

	public static boolean naoTemPermissao(List<Permissao> permissoesUsarioLogado,
			List<Permissao> permissoesUsuarioSalvo) {
		boolean administradorLogado = isAdministrador(permissoesUsarioLogado);
		boolean administradorSalvo = isAdministrador(permissoesUsuarioSalvo);

		return !administradorLogado && administradorSalvo;
	}

	private static boolean isAdministrador(List<Permissao> permissoes) {
		return permissoes.stream().anyMatch(p -> p.getId().equals(ROLE_ADMINISTRADOR.id));
	}

}
