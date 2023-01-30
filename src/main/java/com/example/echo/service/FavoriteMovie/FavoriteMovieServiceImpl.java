package com.example.echo.service.FavoriteMovie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.select.FavoriteMovie;
import com.example.echo.repository.FavoriteMovieRepository;


@Service
@Transactional
public class FavoriteMovieServiceImpl implements FavoriteMovieService {

    @Autowired
    FavoriteMovieRepository inst;

    public Iterable<FavoriteMovie> OrderFavoriteMovie(String user_id) {

        return inst.OrderFavoriteMovie(user_id);
    }

    public void DeleteFavoriteMovie(String user_id, String movie_id) { 
        inst.delete(user_id, movie_id);
    }

        
    public void InsertFavoriteMovie(String user_id, String movie_id) { 
        inst.insert(user_id, movie_id);
    }

    public Boolean CheckDeplicate(String user_id, String movie_id) {
        Integer count = inst.CheckDate(user_id, movie_id);
        boolean result = false;
        if(count == 0) {
            result = true;
        }
        return result;
    }

    public Boolean FavoriteMovieCount(String user_id) {
        Integer count = inst.CountFavoriteMovie(user_id);
        boolean result = false;
        if(count < 3) {
            result = true;
        }
        return result;
    }
}
