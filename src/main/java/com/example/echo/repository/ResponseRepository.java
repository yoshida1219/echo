package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.Response;

public interface ResponseRepository extends CrudRepository<Response,String>{

    @Query("select user.USER_NAME,RESPONSE_NAME,response.like,response.share " + 
           "from response " + 
           "inner join user on RESPONSE_CREATER = USER_ID " +
           "where RESPONSE_ID = :response_id and RESPONSE_CREATER = :user_id;")
    Iterable<Response> SelectResponse(
        @Param("response_id") String response_id,
        @Param("user_id") String user_Id
    );

    //ジャンル不問の人気動画を呼び出す
    @Query("select user.user_id,response.response_id,response.response_name, movie.url, movie.thumbnail, user.user_name, response.like " +
    "from response " +
    "inner join user on user.user_id = response.response_creater " +
    "inner join movie on movie.movie_id = response.movie_id " +
    "order by response.like desc limit 0, 5;")
    Iterable<Response> OrderPopularMovie();

    //ジャンルごとの新着スレッドを呼び出す
    @Query("with pickup_movie as ( " +

        "select * from ( " +
		"select thread.jenre_id, thread.thread_id, thread.thread_name, thread.thread_creater, response.response_creater, response.response_id, response.response_name, response.response_submit, movie.movie_id, coalesce(movie.thumbnail, '/img/のーいめーじ.jpg') as thumbnail, " +
		"case when response.response_id is null then 1 else row_number() over(partition by response.thread_id order by response.like desc) end as 'rank' " +
		"from thread " +
		"left outer join response on response.thread_id = thread.thread_id " +
		"left outer join movie on movie.movie_id = response.movie_id " +
		"order by response.response_submit desc " +
	    ") as temp " +
	    "where temp.rank = 1 " +
        ") " +
        ", new_movie as ( " +
	    "select * from ( " +
		"select thread.jenre_id, thread.thread_id, thread.thread_submit, response.response_creater, response.response_id, response.response_name, response.response_submit, row_number() over(partition by thread.jenre_id order by thread.thread_submit desc) as 'new1' " +
		"from thread " +
		"left outer join response on response.thread_id = thread.thread_id " +
		"order by response.response_submit desc " +
	    ") as temp " +
	    "where temp.new1 <= 5 " +

        ") " +
        "select new_movie.jenre_id, new_movie.thread_id, new_movie.thread_submit, new_movie.response_creater, new_movie.response_id, new_movie.response_name, new_movie.response_submit, new_movie.new1, pickup_movie.movie_id, pickup_movie.thumbnail " +
        "from new_movie " +
        "left outer join pickup_movie on pickup_movie.thread_id = new_movie.thread_id " +
        "order by new_movie.jenre_id, new_movie.new1 limit 4;")
    Iterable<Response> OrderNewThread();

    //ユーザごとの　response_id の最大値を取り出す
    @Query("SELECT MAX(response_id) FROM response WHERE response_creater = :user_id;")
    String findMaxResponseId(
        @Param("user_id") String user_id
    );

    //コメントできるようにするためのにview_responseに登録されているかどうか判定する
    @Query("select count(*) as count from view_response where response_id = :response_id and response_creater = :response_creater and view_user = :view_user;")
    String FirstTimeView(
        @Param("response_id") String response_id,
        @Param("response_creater") String response_creater,
        @Param("view_user") String view_user
    );

    @Modifying
    @Query("INSERT INTO response VALUES(:response_creater, :response_id, :response_name, :thread_id, :movie_id, 0, 0, now(), null, null)")
    String insert(
        @Param("response_creater") String response_creater,
        @Param("response_id") String response_id,
        @Param("response_name") String response_name,
        @Param("thread_id") String thread_id,
        @Param("movie_id") String movie_id
    );

    @Modifying
    /*
    @Query(" insert into response"
        + " select 'U00000001' as 'temp_creater', 'R000024' as 'temp_id', 'この投稿は共有されたものです', null, movie_id, 0, 0, now(), coalesce(origin_creater, response_creater), coalesce(origin_id, response_id)"
        + " from response where response_creater = :response_creater and response_id = :response_id;")
    */
    @Query("insert into response"
        + " select replace(response_creater, :response_creater, :login_user_id) as 'temp1', replace(response_id, :response_id, :login_user_response) as 'temp2', 'この投稿は共有されたものです', null, movie_id, 0, 0, now(), coalesce(origin_creater, response_creater), coalesce(origin_id, response_id)"
        + " from ("
        + " select * from response where response_creater=:response_creater and response_id=:response_id"
        + " ) a")

    void ShareInsert(
        @Param("login_user_id") String login_user_id,
        @Param("login_user_response") String login_user_response,
        @Param("response_creater") String response_creater,
        @Param("response_id") String response_id
    );
    
    @Modifying
    @Query("insert into view_response value(:response_id,:response_creater,:view_user,0, now());")
    void insertView_Response(
        @Param("response_id") String response_id,
        @Param("response_creater") String response_creater,
        @Param("view_user") String view_user
    );

    @Modifying
    @Query("delete from response where response_creater = :response_creater and response_id = :response_id;")
    void deleteResponse(
        @Param("response_creater") String response_creater,
        @Param("response_id") String response_id
    );

}




