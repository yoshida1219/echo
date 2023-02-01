package com.example.echo.service.Movie;

import java.util.Optional;

import com.example.echo.entity.Movie;

public interface MovieService {
    
    Optional<Movie> SelectMovie(String movie_id);

    String SelectMaxMovieId();

    void insert(Movie movie);

    Optional<Movie> selectMovieId(String url);

    Optional<Movie> existsMovie(String url);

    void updateThumbnail(String url, String thumbnail);
}
