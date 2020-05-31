package com.greenfoxacademy.zerothweekproject.services;

import com.greenfoxacademy.zerothweekproject.modells.daos.MovieData;
import java.io.IOException;

public interface MovieService {
  MovieData getMovie(Integer movieId) throws IOException;
  void saveMovie(MovieData movieData);
}
