package com.example.echo.service.Home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.select.SelectFollowerMovie;
import com.example.echo.repository.HomeSelectRepository;

@Service
@Transactional
public class HomeServiceImpl implements HomeService {
    @Autowired
    HomeSelectRepository repository;

    @Override
    public Iterable<SelectFollowerMovie> selectFollowerMovie(String user_id) {
        return repository.findFollowerMovies(user_id);
    }
}
