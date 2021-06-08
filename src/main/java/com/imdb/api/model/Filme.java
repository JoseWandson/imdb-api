package com.imdb.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "filme")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Filme {

	@Id
	@EqualsAndHashCode.Include
	private Long id;
	private String nome;
	private String diretor;
	private String genero;

	@OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Voto> votos;

	@ElementCollection
	@Column(name = "ator")
	@CollectionTable(name = "filme_ator", joinColumns = @JoinColumn(name = "id_filme"))
	private List<String> atores;

	@Transient
	private Double mediaVotos;

}
