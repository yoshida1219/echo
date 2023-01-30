package com.example.echo.service.FavoriteMovie;

import com.example.echo.entity.select.FavoriteMovie;

public interface FavoriteMovieService {
    Iterable<FavoriteMovie> OrderFavoriteMovie(String user_id);

    void DeleteFavoriteMovie(String user_id, String movie_id);

    void InsertFavoriteMovie(String user_id, String movie_id);

    Boolean CheckDeplicate(String user_id, String movie_id);

    Boolean FavoriteMovieCount(String user_id);

}
