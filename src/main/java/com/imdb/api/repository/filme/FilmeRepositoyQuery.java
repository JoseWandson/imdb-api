package com.imdb.api.repository.filme;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.imdb.api.model.Filme;
import com.imdb.api.repository.filter.FilmeFilter;

public interface FilmeRepositoyQuery {

	Page<Filme> filtrar(FilmeFilter filmeFilter, Pageable pageable);

}
