package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.User;
import com.example.echo.entity.select.Notice;

public interface NoticeRepository extends CrudRepository<NoticeRepository, String> {

    @Query("""
         select user.user_id, user.user_name, user.icon, 'follow' as 'reason', follow.follow_time as 'time', null as response_id, null as url 
         from user
         inner join follow on follow.user_id = user.user_id
         where follow.followuser_id = 'U00000001'
         and follow.notification = 1
         union
         select user.user_id, user.user_name, user.icon, 'comment', comment.submit_time as 'time', comment.response_id, movie.url
         from comment
         inner join user on user.user_id = comment.view_user
         inner join response on response.response_creater = comment.response_creater and response.response_id = comment.response_id
         inner join movie on movie.movie_id = response.movie_id
         where comment.response_creater='U00000001'
         and comment.notification='1'
         and comment.view_user!='U00000001'
         order by time desc;
         """
    )
    Iterable<Notice> OrderNoticeFollow(@Param("user_id") String user_id);

    @Modifying
    @Query("UPDATE follow SET notification=0 where user_id=:user_id and followuser_id=:login_user_id")
    void update(
        @Param("user_id") String user_id,
        @Param("login_user_id") String login_user_id
    );

    
    @Modifying
    @Query("UPDATE comment SET notification=0 where response_creater=:login_user_id and response_id=:response_id;")
    void updateComment(
        @Param("login_user_id") String response_creater,
        @Param("response_id") String response_id
    );


    
}
