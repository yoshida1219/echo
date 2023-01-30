package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.SelectResponse;

public interface SelectResponseRepository extends CrudRepository<SelectResponse,String>{

    @Query("with like_count as ( select response_creater, response_id, sum(view_like) as 'like_count' from view_response tmp1 group by response_creater, response_id ), "
        + "share_count as ( select count(*) as 'share_count', origin_creater as response_creater, origin_id as response_id from response tmp2 group by origin_creater, origin_id) "

        + "select user.user_name, user.user_id, user.icon, response.response_name "
        + " , coalesce(like_count.like_count, 0) as 'like', coalesce(share_count.share_count, 0) as 'share' " 
        + "from response " 
        + "inner join user on user.user_id = response.response_creater " 
        
        + "left outer join like_count on like_count.response_creater = response.response_creater and like_count.response_id = response.response_id "
        + "left outer join share_count on share_count.response_creater = response.response_creater and share_count.response_id = response.response_id "

        + "where response.response_id = :response_id and response.response_creater = :user_id;")
    Iterable<SelectResponse> SelectResponse(
        @Param("response_id") String response_id,
        @Param("user_id") String user_Id
    );


    @Query("select response.thread_id " + 
           "from response " +
           "where response_id = :response_id and response_creater = :user_id;")
    String SelectThread_id(
        @Param("response_id") String response_id,
        @Param("user_id") String user_id
    );

    @Query("select response.Movie_id " + 
           "from response " +
           "where response_id = :response_id and response_creater = :user_id;")
    String SelectMovie_id(
        @Param("response_id") String response_id,
        @Param("user_id") String user_id
    );

    //ジャンル不問の人気動画を呼び出す
    @Query("select user.user_id,response.response_id,response.response_name, movie.url, movie.thumbnail, user.user_name, response.like " +
    "from response " +
    "inner join user on user.user_id = response.response_creater " +
    "inner join movie on movie.movie_id = response.movie_id " +
    "order by response.like desc limit 0, 5;")
    Iterable<SelectResponse> OrderPopularMovie();

    //ジャンルごとの新着スレッドを呼び出す
    @Query("with new_thread as ( " +
	        "select *, row_number() over(partition by temp.jenre_id order by temp.thread_submit desc) as 'new_th' " +
	        "from ( " +
		    "select thread.jenre_id, thread.thread_id, thread.thread_name, thread.thread_submit, thread.thread_creater, response.response_creater, response.response_id, response.response_name, movie.movie_id, coalesce(movie.thumbnail, '/img/のーいめーじ.jpg') as thumbnail, " +
		    "case when response.response_id is null then 1 else row_number() over(partition by response.thread_id order by response.like desc) end as 'rank' " +
		    "from thread " +
		    "left outer join response on response.thread_id = thread.thread_id " +
	        "left outer join movie on movie.movie_id = response.movie_id " +
	        ") as temp where temp.rank = 1) " + 
            "select * from new_thread where new_thread.new_th <= 5 and new_thread.jenre_id = :jenre_id;")
    Iterable<SelectResponse> OrderNewThread(@Param("jenre_id") String jenre_id);

    //ユーザごとの　response_id の最大値を取り出す
    @Query("SELECT MAX(response_id) FROM response WHERE response_creater = :user_id;")
    String findMaxResponseId(
        @Param("user_id") String user_id
    );

    @Query("INSERT INTO response VALUES(:response_creater, :response_id, :response_name, :thread_id, :movie_id))")
    String insert(
        @Param("response_creater") String response_creater,
        @Param("response_id") String response_id,
        @Param("response_name") String response_name,
        @Param("thread_id") String thread_id,
        @Param("movie_id") String movie_id
    );
}




