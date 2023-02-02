package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.Follow;

public interface FollowRepository extends CrudRepository<Follow,String>{

    @Query("select user.user_id, user.user_name, introduction, icon "
         + "from follow "
         + "inner join user on user.user_id = follow.followuser_id "
         + "where follow.user_id = :user_id;")
    Iterable<Follow> selectFollow(
        @Param("user_id") String user_id
    );
    
    @Query("select count(*) as follow_judgement "
           + "from follow " 
           + "where user_id = :user_id "
           + "and followuser_id = :followuser_id;")
    int follow_judgement(
        @Param("user_id") String user_id,
        @Param("followuser_id") String followuser_id
    );

    @Modifying
    @Query("insert into follow value(:user_id, :followuser_id, now(), 0);")
    void insertFollow(
        @Param("user_id") String user_id,
        @Param("followuser_id") String followuser_id
    ); 

    @Modifying
    @Query("delete from follow where user_id = :user_id and followuser_id = :followuser_id;")
    void deleteFollow(
        @Param("user_id") String user_id,
        @Param("followuser_id") String followuser_id
    );
}
