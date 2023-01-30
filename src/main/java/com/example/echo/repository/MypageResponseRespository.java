package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.MypageResponse;

public interface MypageResponseRespository extends CrudRepository<MypageResponseRespository, String> {
    @Query("select response.response_creater as 'user_id', response.response_id, response.response_name, response.response_submit, thread.thread_id, thread.thread_name, movie.movie_id, movie.movie_name, coalesce(movie.thumbnail, '/img/のーいめーじ.jpg') as thumbnail, movie.url "
    + "from response " 
    + "inner join movie on movie.movie_id = response.movie_id " 
    + "left outer join thread on thread.thread_id = response.thread_id " 
    + "where response.response_creater = :user_id "
    + "order by response.response_submit desc"
    + ";")
    Iterable<MypageResponse> findMypageResponse(@Param("user_id") String user_id);
    
}