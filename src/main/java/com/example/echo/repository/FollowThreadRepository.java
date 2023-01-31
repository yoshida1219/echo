package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.FollowThread;

public interface FollowThreadRepository extends CrudRepository<FollowThreadRepository,String>{
    //SQL_TEST㉙を使用
    //フォローしているスレッドを呼び出す
    @Query("with pickup_movie as ("
    + " select * from ("
    + " select * ,case when response.response_id is null then 1 else row_number() over(partition by response.thread_id order by like_count desc) end as 'rank'"
    + " from thread"
    + " left outer join response using(thread_id)"
    + " left outer join movie using(movie_id)"
    + " left outer join ( select response_creater, response_id, sum(view_like) as 'like_count' from view_response tmp1 group by response_creater, response_id ) like_count using(response_creater, response_id)"
    + " order by response.response_submit desc"
    + " ) as temp where temp.rank = 1), "
    + " new_movie as ("
    + " select * from ("
    + " select * , row_number() over(partition by response.thread_id order by response.response_submit desc) as 'new'"
    + " from thread"
    + " left outer join response using(thread_id)"
    + " order by response.response_submit desc"
    + " ) as temp where temp.new = 1)"
    + " select thread.thread_id, thread.thread_name, thread.thread_creater, p.response_creater, p.response_id, p.response_name, p.movie_id, p.url, coalesce(p.thumbnail, '/img/のーいめーじ.jpg') as thumbnail, new_movie.response_submit, "
    + " user.user_id, user.user_name, user.icon, thread.thread_submit, jenre.jenre_name "
    + " from thread " 
    + " inner join user on user.user_id = thread.thread_creater"
    + " inner join jenre on jenre.jenre_id = thread.jenre_id"
    + " inner join follow_thread on follow_thread.thread_id = thread.thread_id"
    + " left outer join pickup_movie as p on p.thread_id = thread.thread_id"
    + " left outer join new_movie on new_movie.thread_id = thread.thread_id"
    + " where follow_thread.user_id = :user_id"
    + " order by new_movie.response_submit desc limit 0, 5;")
    Iterable<FollowThread> OrderFollowThread(@Param("user_id") String user_id);
}