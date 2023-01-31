package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.MyThread;

public interface MyThreadRepository extends CrudRepository<MyThread,String>{
    //SQL_TEST㉜を使用
    //自分作成したスレッドを呼び出す
    @Query("with p as (select * from ( "
    + "select thread.thread_id, thread.thread_name, thread.thread_creater, jenre.jenre_name, thread.thread_submit, coalesce(movie.thumbnail, '/img/のーいめーじ.jpg') as thumbnail, response.response_creater, response.response_id, "
    + "case when response.response_id is null then 1 else row_number() over(partition by response.thread_id order by like_count desc) end as 'rank' "
    + "from thread inner join jenre using(jenre_id) "
    + "left outer join response on response.thread_id = thread.thread_id "
    + "left outer join movie on movie.movie_id = response.movie_id "
    + " left outer join ( select response_creater, response_id, sum(view_like) as 'like_count' from view_response tmp1 group by response_creater, response_id ) like_count using(response_creater, response_id)"
    + "where thread.thread_creater = :user_id "
    + "order by response.response_submit desc, thread.thread_submit desc) as temp where temp.rank = 1 ), "
    + "new_movie as ( "
    + "select thread.thread_id, thread.thread_name, thread.thread_creater, response.response_creater, response.response_id, response.response_name, response.response_submit "
    + ", case when response.response_id is null then 1 else  row_number() over(partition by response.thread_id order by response.response_submit desc) end as 'new' "
    + "from thread "
    + "left outer join response on response.thread_id = thread.thread_id "
    + " where thread.thread_creater = :user_id "
    + " order by response.response_submit desc, thread.thread_submit desc) "
    + "select n.thread_name, n.thread_id, user.user_id, user.user_name, user.icon, p.thumbnail, p.thread_submit, p.jenre_name, n.new, p.rank "
    + "from thread "
    + "inner join new_movie as n on n.thread_id = thread.thread_id "
    + "inner join user on user.user_id = n.thread_creater "
    + "left outer join p on p.thread_id = thread.thread_id "
    + "where n.new = 1 "
    + "order by n.response_submit desc limit 0, 5 "
    + ";")
    Iterable<MyThread> OrderMyThread(@Param("user_id") String user_id);
}