package com.greenfoxacademy.zerothweekproject.controllers;

import com.greenfoxacademy.zerothweekproject.modells.dtos.AuthenticationRequest;
import com.greenfoxacademy.zerothweekproject.modells.dtos.AuthenticationResponse;
import com.greenfoxacademy.zerothweekproject.services.MovieService;
import com.greenfoxacademy.zerothweekproject.services.MyUserDetailsService;
import com.greenfoxacademy.zerothweekproject.services.UserService;
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

@RestController
public class MainController {
  private AuthenticationManager authenticationManager;
  private MyUserDetailsService userDetailsService;
  private UserService userService;
  private JwtUtil jwtUtil;
  private MovieService movieService;

  @Autowired
  public MainController(
      AuthenticationManager authenticationManager,
      MyUserDetailsService userDetailsService, UserService userService,
      JwtUtil jwtUtil, MovieService movieService) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.userService = userService;
    this.jwtUtil = jwtUtil;
    this.movieService = movieService;
  }

  @GetMapping("/register/{name}/{password}")
  public void registerUserWithNameAndPassword(@PathVariable("name") String name,
                                              @PathVariable("password") String password) {
    userService.registerUser(name, password);
  }

  @PostMapping("/authenticate")
  public ResponseEntity<?> createAuthenticationToken(@RequestBody
                                                         AuthenticationRequest authenticationRequest)
  {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authenticationRequest
              .getUsername(), authenticationRequest.getPassword()));
    } catch (BadCredentialsException bce) {
      throw new BadCredentialsException("Incorrect username or password", bce);
    }
    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
    String jwt = jwtUtil.generateToken(userDetails);
    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

    @GetMapping("/getmovie")
    public ResponseEntity<?> getMovie() throws IOException {
      return ResponseEntity.ok(movieService.getMovie());
  }
}