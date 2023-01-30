package com.example.echo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.CrudRepository;

import com.example.echo.entity.select.SelectFollowerMovie;

//import com.example.echo.entity.select.SelectFollowerMovie;

public interface FollowuserRepository extends CrudRepository<SelectFollowerMovie,String>{
    @Query("select count(*) " 
            + "from follow" 
            + "inner join user as user1 on user1.user_id = follow.user_id" 
            + "inner join user as user2 on user2.user_id = follow.followuser_id"
            + "where user1.user_id = 'U00000002';")
    Optional<Integer> findFollowerUsers();
}
