package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.Follower;

public interface FollowerListRpository extends CrudRepository<Follower,String>{
    
    @Query("with follow_user as ("
         + "select follow.followuser_id as follow_login_user from follow where follow.user_id= :session_user_id "
         + ")"
         + "select user.user_id, user.user_name, user.introduction, user.icon , case when follow.user_id= :session_user_id then -1 when follow_login_user is null then 0 else 1 end as 'check_follow' from follow "
         + "left outer join follow_user on follow_user.follow_login_user = follow.user_id "
         + "inner join user on follow.user_id = user.user_id "
         + "where follow.followuser_id= :user_id ;")
    Iterable<Follower> OederFollowerList(@Param("user_id") String user_id,
                                         @Param("session_user_id") String session_user_id);

}
