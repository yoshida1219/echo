package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.Follow;

public interface FollowRepository extends CrudRepository<Follow,String>{

    @Query("select user.user_name, introduction, icon "
            + "from follow "
            + " inner join user on user.user_id = follow.user_id "
            + "where follow.followuser_id = :user_id;")
    Iterable<Follow> selectFollow(
        @Param("user_id") String user_id
    );
    
}
