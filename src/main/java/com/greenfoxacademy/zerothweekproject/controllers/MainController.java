package com.greenfoxacademy.zerothweekproject.controllers;

import com.greenfoxacademy.zerothweekproject.modells.daos.MovieData;
import com.greenfoxacademy.zerothweekproject.modells.dtos.AuthenticationRequest;
import com.greenfoxacademy.zerothweekproject.modells.dtos.AuthenticationResponse;
import com.greenfoxacademy.zerothweekproject.services.ApiInterface;
import com.greenfoxacademy.zerothweekproject.services.MyUserDetailsService;
import com.greenfoxacademy.zerothweekproject.utilities.JwtUtil;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RestController
public class MainController {
  private AuthenticationManager authenticationManager;
  private MyUserDetailsService userDetailsService;
  private JwtUtil jwtUtil;
  public static String BASE_URL = "https://api.themoviedb.org";
  private String LANGUAGE = "en-US";
  private String API_KEY = "9818383aba797e6caf0bb0c1dbe33f52";
  private int PAGE = 1;

  @Autowired
  public MainController(
      AuthenticationManager authenticationManager,
      MyUserDetailsService userDetailsService,
      JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtUtil = jwtUtil;
  }

  @GetMapping("/register/{name}/{password}")
  public void registerUserWithNameAndPassword(@PathVariable("name") String name,
                                              @PathVariable("password") String password) {
    userDetailsService.registerUser(name, password);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody
                                                         AuthenticationRequest authenticationRequest)
      throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authenticationRequest
              .getUsername(), authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password", e);
    }
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    String jwt = jwtUtil.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

  @GetMapping("/getmovie")
  public ResponseEntity<?> getMovie() throws IOException {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    ApiInterface apiInterface = retrofit.create(ApiInterface.class);
    Call<MovieData> call = apiInterface.getMovieData(API_KEY, LANGUAGE, PAGE);
    Response<MovieData> response = call.execute();
    return ResponseEntity.ok(response.body());
  }
}