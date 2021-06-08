package com.imdb.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imdb.api.model.Voto;
import com.imdb.api.model.VotoId;

@Repository
public interface VotoRepository extends JpaRepository<Voto, VotoId> {

}
