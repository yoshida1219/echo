package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.User;

public interface NoticeRepository extends CrudRepository<NoticeRepository, String> {

    @Query("""
         select *
         from user
         inner join follow on follow.user_id = user.user_id
         where follow.followuser_id = :user_id
         and follow.notification = 1;
         """
    )
    Iterable<User> OrderNoticeFollow(@Param("user_id") String user_id);

    @Modifying
    @Query("UPDATE follow SET notification=0 where user_id=:user_id and followuser_id=:login_user_id")
    void update(
        @Param("user_id") String user_id,
        @Param("login_user_id") String login_user_id
    );



    
}
