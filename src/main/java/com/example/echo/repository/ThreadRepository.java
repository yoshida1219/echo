package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.Thread;

public interface ThreadRepository extends CrudRepository<Thread, String>{
    
    @Query(
        "SELECT thread_id FROM thread ORDER BY thread_id desc LIMIT 1"
    )
    String findMaxThread_id();


    @Query(
        "select jenre_name "
      + "from thread "
      + "inner join jenre "
      + "on jenre.jenre_id = thread.jenre_id "
      + "where thread.thread_id = :thread_id"
    )
    String findThreadJenre_name(
        @Param("thread_id") String thread_id
    );



    @Modifying
    @Query("insert into thread value(:thread_id, :thread_name, :thread_creater, :genre_id,now());")
    void insertThread(
        @Param("thread_id") String thread_id,
        @Param("thread_name") String thread_name,
        @Param("thread_creater") String thread_creater,
        @Param("genre_id") String genre_id
    );


    @Modifying
    @Query("insert into follow_thread value(:user_id, :thread_id, now());")
    void insertFollowThread(
    @Param("user_id") String user_id, @Param("thread_id") String thread_id
            );

    @Modifying
    @Query("delete from follow_thread where user_id=:user_id and thread_id=:thread_id ;")
    void deleteFollowThread(
        @Param("user_id") String user_id, @Param("thread_id") String thread_id
    );



    @Query("select count(*) from follow_thread where user_id=:user_id and thread_id=:thread_id")
    Integer followCheck(
        @Param("user_id") String user_id, @Param("thread_id") String thread_id
    );

    @Modifying
    @Query("delete from thread where thread_id = :thread_id;")
    void deleteThread(
        @Param("thread_id") String thread_id
    );

}
