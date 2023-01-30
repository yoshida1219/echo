package com.example.echo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.Follower;

public interface FollowerRepository extends CrudRepository<FollowerRepository, Integer> {

    @Query("select count(*) as 'follower' " + 
        "from follow " +
        "inner join user as user1 on user1.user_id = follow.user_id " +
        "inner join user as user2 on user2.user_id = follow.followuser_id " +
        "where user1.user_id = :user_id;" )
        Optional<Follower> OrderFollower(@Param("user_id") String user_id);
    
    @Query("select count(*) as 'follower' " + 
        "from follow " +
        "inner join user as user1 on user1.user_id = follow.user_id " +
        "inner join user as user2 on user2.user_id = follow.followuser_id " +
        "where user2.user_id = :user_id;" )
        Optional<Follower> OrderFollowerPeople(@Param("user_id") String user_id);

    @Query("select count(*) from follow where user_id = :user_id and followuser_id = :follow_user_id")
        Optional<Integer> OrderCheckFollow(@Param("user_id") String user_id, @Param("follow_user_id") String follow_user_id);


    @Modifying
    @Query("INSERT INTO follow VALUES(:user_id, :follow_user_id, now(), 1)")
        void insert(
        @Param("user_id") String user_id,
        @Param("follow_user_id") String follow_user_id
    );

    @Modifying
    @Query("DELETE FROM follow  WHERE user_id = :user_id and followuser_id = :follow_user_id;")
        void delete(
        @Param("user_id") String user_id,
        @Param("follow_user_id") String follow_user_id
    );
    
}
