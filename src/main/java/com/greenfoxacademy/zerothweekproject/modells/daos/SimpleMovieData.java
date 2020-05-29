package com.greenfoxacademy.zerothweekproject.modells.daos;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "moviedata")
public class SimpleMovieData {
  @Id
  private Integer id;
  private String title;
  private String releaseDate;

  public SimpleMovieData() {
  }

  public SimpleMovieData(Integer id, String title, String releaseDate) {
    this.id = id;
    this.title = title;
    this.releaseDate = releaseDate;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }
}
