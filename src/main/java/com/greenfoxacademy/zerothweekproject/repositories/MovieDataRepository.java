package com.greenfoxacademy.zerothweekproject.repositories;

import com.greenfoxacademy.zerothweekproject.modells.daos.SimpleMovieData;
import org.springframework.data.repository.CrudRepository;

public interface MovieDataRepository extends CrudRepository<SimpleMovieData, Integer> {
}
