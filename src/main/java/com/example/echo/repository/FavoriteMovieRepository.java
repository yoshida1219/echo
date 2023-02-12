package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.FavoriteMovie;

public interface FavoriteMovieRepository extends CrudRepository<FavoriteMovieRepository, String> {

    @Query("select favorite_movie.user_id, favorite_movie.response_id, movie.movie_id, movie.movie_name, movie.url, movie.thumbnail, case when DATE_FORMAT(movie.movie_time, '%H')='00' then DATE_FORMAT(movie.movie_time, '%i:%s') else DATE_FORMAT(movie.movie_time, '%H:%i:%s') end 'movie_time' from favorite_movie inner join movie on favorite_movie.movie_id = movie.movie_id where favorite_movie.user_id = :user_id;")
    Iterable<FavoriteMovie> OrderFavoriteMovie(@Param("user_id") String user_id);

    @Modifying
    @Query("DELETE FROM favorite_movie WHERE user_id=:user_id AND response_id=:response_id;")
    void delete(
        @Param("user_id") String user_id, 
        @Param("response_id") String response_id
    );

    @Modifying
    @Query("INSERT INTO favorite_movie VALUES(:user_id, :response_id, now(), :movie_id);")
    void insert(
        @Param("user_id") String user_id, 
        @Param("response_id") String response_id,
        @Param("movie_id") String movie_id
    );

    @Query("select count(*) from favorite_movie where user_id=:user_id and response_id=:response_id;")
    Integer CheckDate(
        @Param("user_id") String user_id, 
        @Param("response_id") String response_id
    );

    @Query("select count(*) from favorite_movie where user_id=:user_id;")
    Integer CountFavoriteMovie(
        @Param("user_id") String user_id
    );
}
