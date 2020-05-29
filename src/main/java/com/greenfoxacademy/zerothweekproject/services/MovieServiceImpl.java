package com.greenfoxacademy.zerothweekproject.services;

import com.greenfoxacademy.zerothweekproject.modells.daos.SimpleMovieData;
import com.greenfoxacademy.zerothweekproject.modells.daos.MovieData;
import com.greenfoxacademy.zerothweekproject.repositories.MovieDataRepository;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class MovieServiceImpl implements MovieService {
  private MovieDataRepository movieDataRepository;

  @Autowired
  public MovieServiceImpl(
      MovieDataRepository movieDataRepository) {
    this.movieDataRepository = movieDataRepository;
  }

  protected static String BASE_URL = "https://api.themoviedb.org";
  private String LANGUAGE = "en-US";
  private String API_KEY = "9818383aba797e6caf0bb0c1dbe33f52";
  private int PAGE = 1;

  @Override
  public MovieData getMovie() throws IOException {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    ApiInterface apiInterface = retrofit.create(ApiInterface.class);
    Call<MovieData> call = apiInterface.getMovieData(API_KEY, LANGUAGE, PAGE);
    Response<MovieData> response = call.execute();
    saveMovie(response.body());
    return response.body();
  }

  @Override
  public void saveMovie(MovieData movieData) {
    SimpleMovieData simpleMovieData =
        new SimpleMovieData(movieData.getId(), movieData.getTitle(), movieData.getReleaseDate());
    movieDataRepository.save(simpleMovieData);
  }
}
