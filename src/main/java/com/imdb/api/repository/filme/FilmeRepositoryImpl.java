package com.imdb.api.repository.filme;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.imdb.api.model.Filme;
import com.imdb.api.model.Filme_;
import com.imdb.api.repository.filter.FilmeFilter;

public class FilmeRepositoryImpl implements FilmeRepositoyQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Filme> filtrar(FilmeFilter filmeFilter, Pageable pageable) {
		var builder = manager.getCriteriaBuilder();
		CriteriaQuery<Filme> criteria = builder.createQuery(Filme.class);
		Root<Filme> root = criteria.from(Filme.class);

		Predicate[] predicates = criarRestricoes(filmeFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Filme> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(filmeFilter));
	}

	private Predicate[] criarRestricoes(FilmeFilter filmeFilter, CriteriaBuilder builder, Root<Filme> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (StringUtils.hasText(filmeFilter.getDiretor())) {
			predicates.add(builder.like(builder.lower(root.get(Filme_.diretor)),
					"%" + filmeFilter.getDiretor().toLowerCase() + "%"));
		}

		if (StringUtils.hasText(filmeFilter.getNome())) {
			predicates.add(builder.like(builder.lower(root.get(Filme_.nome)),
					"%" + filmeFilter.getNome().toLowerCase() + "%"));
		}

		if (StringUtils.hasText(filmeFilter.getGenero())) {
			predicates.add(builder.like(builder.lower(root.get(Filme_.genero)),
					"%" + filmeFilter.getGenero().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<Filme> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(FilmeFilter filmeFilter) {
		var builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Filme> root = criteria.from(Filme.class);

		Predicate[] predicates = criarRestricoes(filmeFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
