package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.Follower;

public interface FollowerListRpository extends CrudRepository<Follower,String>{
    
    @Query("select user.user_name, introduction, icon "
     + "from follow "
     + "inner join user on user.user_id = follow.followuser_id "
     + "where follow.user_id = :user_id;")
    Iterable<Follower> OederFollowerList(@Param("user_id") String user_id);

}
