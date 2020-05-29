package com.greenfoxacademy.zerothweekproject.services;

import com.greenfoxacademy.zerothweekproject.modells.daos.MovieData;
import java.io.IOException;

public interface MovieService {
  MovieData getMovie() throws IOException;
  void saveMovie(MovieData movieData);
}
