package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.PopularThread;

public interface PopularThreadRepository extends CrudRepository<PopularThread,String>{
    
    //SQL_TEST㉘を使用
    //ジャンルごとの人気のスレッドを呼び出す
    @Query("with pop_thread as ( "
	        + " select *, row_number() over(partition by temp.jenre_id order by temp.th_num desc) as 'pop_th'"
	        + " from ("
		    + " select thread.jenre_id, thread.thread_id, thread.thread_name, thread.thread_creater, response.response_creater, response.response_id, response.response_name, movie.movie_id, thread_count.th_num, coalesce(movie.thumbnail, '/img/のーいめーじ.jpg') as thumbnail,"
		    + " case when response.response_id is null then 1 else row_number() over(partition by response.thread_id order by like_count desc) end as 'rank'"
		    + " from thread	left outer join response on response.thread_id = thread.thread_id"
		    + " left outer join (select response.thread_id, count(*) as th_num from response group by thread_id) as thread_count on thread_count.thread_id = response.thread_id"
		    + " left outer join movie on movie.movie_id = response.movie_id"
			+ " left outer join ( select response_creater, response_id, sum(view_like) as 'like_count' from view_response tmp1 group by response_creater, response_id ) like_count using(response_creater, response_id)"
	        + " ) as temp where temp.rank = 1 )"
            + " select * from pop_thread where pop_thread.pop_th <= 5 and pop_thread.jenre_id = :jenre_id;")
    Iterable<PopularThread> OrderPopularThread(@Param("jenre_id") String jenre_id);
}
