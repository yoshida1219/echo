package com.example.echo.service.Home;

import java.lang.Iterable;

import com.example.echo.entity.select.SelectFollowerMovie;

public interface HomeService {
    Iterable<SelectFollowerMovie> selectFollowerMovie(String user_id);
}
