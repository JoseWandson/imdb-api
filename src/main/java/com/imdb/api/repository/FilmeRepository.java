package com.imdb.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imdb.api.model.Filme;
import com.imdb.api.repository.filme.FilmeRepositoyQuery;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>, FilmeRepositoyQuery {

}
