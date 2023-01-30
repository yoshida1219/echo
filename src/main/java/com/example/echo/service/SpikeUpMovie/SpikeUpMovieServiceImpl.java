package com.example.echo.service.SpikeUpMovie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.select.SpikeUpMovie;
import com.example.echo.repository.SpikeUpMovieRepository;


@Service
@Transactional
public class SpikeUpMovieServiceImpl implements SpikeUpMovieService {

    @Autowired
    SpikeUpMovieRepository inst;

    public Iterable<SpikeUpMovie> FindSpikeUpMovie() {
        return inst.OrderSpikeUpMovie();
    }
}
