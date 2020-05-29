package com.greenfoxacademy.zerothweekproject.services;

import com.greenfoxacademy.zerothweekproject.modells.daos.MovieData;
import java.io.IOException;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Service
public class MovieServiceImpl implements MovieService {
  public static String BASE_URL = "https://api.themoviedb.org";
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
    return response.body();
  }
}
