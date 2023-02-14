package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.SharedThread;

public interface SharedThreadRepository extends CrudRepository<SharedThread,String>{
    @Query("select distinct(thread.thread_id), thread.thread_name, movie.movie_name, coalesce(movie.thumbnail, '/img/のーいめーじ.jpg') as thumbnail " +
           "from thread " +
           "inner join response on thread.thread_id = response.thread_id  " +
           "inner join movie on movie.movie_id = response.movie_id " +
           "where response.movie_id = :movie_id " + 
           "and thread.thread_id != :thread_id " + 
           "order by response.like limit 4;")
    Iterable<SharedThread> selectThread(
        @Param("movie_id") String movie,
        @Param("thread_id") String thread_id
    );
  
}
