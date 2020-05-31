package com.greenfoxacademy.zerothweekproject.services;

import com.greenfoxacademy.zerothweekproject.modells.daos.MovieData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
  //https://api.themoviedb.org/3/movie/6?api_key=9818383aba797e6caf0bb0c1dbe33f52&language=en-US
  @GET("/3/movie/{movieId}")
  Call<MovieData> getMovieData(
      @Path("movieId") Integer movieId,
      @Query("api_key") String apiKey,
      @Query("language") String language
  );
}