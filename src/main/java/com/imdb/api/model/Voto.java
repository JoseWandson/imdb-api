package com.imdb.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "voto")
@IdClass(VotoId.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Voto {

	@Id
	@Column(name = "id_usuario")
	@EqualsAndHashCode.Include
	private Long usuarioId;

	@Id
	@Column(name = "id_filme")
	@EqualsAndHashCode.Include
	private Long filmeId;

	private Integer nota;

	@ManyToOne(optional = false)
	@JoinColumn(name = "usuario_id", insertable = false, updatable = false)
	private Usuario usuario;

	@ManyToOne(optional = false)
	@JoinColumn(name = "filme_id", insertable = false, updatable = false)
	private Filme filme;

}
