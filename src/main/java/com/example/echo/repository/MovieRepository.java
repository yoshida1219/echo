package com.example.echo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie,String>{
    @Query("SELECT * FROM movie where MOVIE_ID = :movie_id;")
    Optional<Movie> selectMovie(@Param("movie_id") String movie_id);

    @Query("SELECT movie_id FROM movie ORDER BY movie_id desc limit 1;")
    String findMaxMovieId();

    @Modifying
    @Query("INSERT INTO movie VALUES(:movie_id, :movie_name, :url, :thumnail)")
    void insert(
        @Param("movie_id") String movie_id,
        @Param("movie_name") String movie_name,
        @Param("url") String url,
        @Param("thumnail") String thumnail
    );

    @Query("SELECT * FROM movie WHERE url = :url")
    Optional<Movie> findMovieId(@Param("url") String url);

    @Query("select url from echo_sns.movie where url = :url;")
    Optional<Movie> existsUrl(@Param("url") String url);
}
