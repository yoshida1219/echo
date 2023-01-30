package com.example.echo.service.Movie;

import java.util.Optional;

import com.example.echo.entity.Movie;

public interface MovieService {
    
    Iterable<Movie> SelectMovie();

    String SelectMaxMovieId();

    void insert(Movie movie);

    Optional<Movie> selectMovieId(String url);

    Optional<Movie> existsMovie(String url);

}
