package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.User;

public interface RecommendRepository extends CrudRepository<RecommendRepository,String>{
    
    
    @Query("with login_user_movie as ("
    + " select *"
    + " from user"
    + " inner join response on response.response_creater = user.user_id"
    + " where user_id = :user_id"
    + " )"
    + " select *"
    + " from user"
    + " inner join ("
    + " select distinct(user.user_id)"
    + " from login_user_movie as login"
    + " inner join response using(movie_id)"
    + " inner join user on user.user_id = response.response_creater"
    + " where response.response_creater != :user_id"
    + " ) "
    + " as share_movie on share_movie.user_id = user.user_id"
    + " where user.user_id not in ("
    + " select follow.followuser_id from follow where follow.user_id = :user_id"
    + " )"
    + " ;")
    Iterable<User> OrderRecommendUser(@Param("user_id") String user_id);

    
}
