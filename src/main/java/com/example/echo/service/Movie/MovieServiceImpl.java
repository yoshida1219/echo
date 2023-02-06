package com.example.echo.service.Movie;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.Movie;
import com.example.echo.repository.MovieRepository;

@Service
@Transactional
public class MovieServiceImpl implements MovieService{
    @Autowired
    MovieRepository repository;

    @Override
    public Optional<Movie> SelectMovie(String movie_id){
        return repository.selectMovie(movie_id);
    }

    @Override
    public String SelectMaxMovieId() {
        return repository.findMaxMovieId();
    }

    @Override
    public void insert(Movie movie) {
        repository.insert(movie.getMovie_id(), movie.getMovie_name(), movie.getUrl(), movie.getThumbnail(), movie.getMovie_time());
    }

        @Override
        public Optional<Movie> selectMovieId(String url) {
            return repository.findMovieId(url);
        }
        public Optional<Movie> existsMovie(String url){
            return repository.existsUrl(url);
        }
    
    @Override
    public void updateThumbnail(String url, String thumbnail) {
        repository.updateThumbnail(url, thumbnail);
    }
    @Override
    public Iterable<Movie> selectMovies() {
        return repository.selectMovies();
    }

    @Override
    public void updateMovie_time(String movie_id, String movie_time) {
        repository.updateMovie_time(movie_id, movie_time);
    }
}
