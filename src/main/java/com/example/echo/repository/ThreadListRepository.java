package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.ThreadList;

public interface ThreadListRepository extends CrudRepository<ThreadList, String>{

    //自作スレッドの登録順
    @Query("with pickup_movie as ("
        + " select *, case when response.response_id is null then 1 else row_number() over(partition by response.thread_id order by like_count desc) end as 'rank' "
        + " from thread inner join jenre using(jenre_id)"
        + " inner join user on thread.thread_creater = user.user_id"
        + " left outer join (select count(*) as follow_count, follow_thread.thread_id from follow_thread group by follow_thread.thread_id) follow_count using(thread_id)"
        + " left outer join (select count(*) as res_count, response.thread_id from response group by response.thread_id) res_count using(thread_id)"
        + " left outer join response using(thread_id)"
        + " left outer join movie using(movie_id)"
        + " left outer join ( select response_creater, response_id, sum(view_like) as 'like_count' from view_response tmp1 group by response_creater, response_id ) like_count using(response_creater, response_id)"
        + " where thread.thread_creater = :user_id"
        + " order by thread.thread_submit desc)"

        + " select p.user_name, p.icon, p.thread_id, p.thread_name, p.jenre_name, p.thread_submit, coalesce(p.thumbnail, '/img/のーいめーじ.jpg') as thumbnail "
        + " , coalesce(p.res_count, 0) as res_count, coalesce(p.follow_count, 0) as follow_count"
        + " from pickup_movie as p where p.rank = 1;")
    Iterable<ThreadList> findMyThread_OrderByRegist(
        @Param("user_id") String user_id
    );

    //自作スレッドの更新順
    @Query("with p as (select * from ( "
        + "select thread.thread_id, thread.thread_name, thread.thread_creater, jenre.jenre_name, thread.thread_submit, coalesce(movie.thumbnail, '/img/のーいめーじ.jpg') as thumbnail, response.response_creater, response.response_id, "
        + " res_count, follow_count, "
        + "case when response.response_id is null then 1 else row_number() over(partition by response.thread_id order by like_count desc) end as 'rank' "
        + "from thread inner join jenre using(jenre_id) "
        + " left outer join (select count(*) as follow_count, follow_thread.thread_id from follow_thread group by follow_thread.thread_id) follow_count using(thread_id)"
        + " left outer join (select count(*) as res_count, response.thread_id from response group by response.thread_id) res_count using(thread_id)"
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
        + " , coalesce(p.res_count, 0) as res_count, coalesce(p.follow_count, 0) as follow_count "
        + "from thread "
        + "inner join new_movie as n on n.thread_id = thread.thread_id "
        + "inner join user on user.user_id = n.thread_creater "
        + "left outer join p on p.thread_id = thread.thread_id "
        + "where n.new = 1 "
        + ";")
    Iterable<ThreadList> findMyThread_OrderByUpdate(
        @Param("user_id") String user_id
    );

    //フォロースレッドの登録順
    @Query("with pickup_movie as (" 
        + " select *, "
        + " case when response.response_id is null then 1 else row_number() over(partition by thread_id order by like_count desc) end as 'rank' "
        + " from thread"
        + " inner join jenre using(jenre_id)"
        + " left outer join (select count(*) as follow_count, follow_thread.thread_id from follow_thread group by follow_thread.thread_id) follow_count using(thread_id)"
        + " left outer join (select count(*) as res_count, response.thread_id from response group by response.thread_id) res_count using(thread_id)"
        + " left outer join response using(thread_id)" 
        + " left outer join movie using(movie_id)"
        + " left outer join user on user.user_id = thread.thread_creater" 
        + " left outer join ( select response_creater, response_id, sum(view_like) as 'like_count' from view_response tmp1 group by response_creater, response_id ) like_count using(response_creater, response_id)"
        + " )"

        + " select p.thread_id, p.thread_creater, p.thread_name, p.url, coalesce(p.thumbnail, '/img/のーいめーじ.jpg') as thumbnail, p.thread_submit, user.user_id, user.user_name, user.icon, p.jenre_id, p.jenre_name "
        + " , coalesce(p.res_count, 0) as res_count, coalesce(p.follow_count, 0) as follow_count "
        + " from pickup_movie as p"
        + " inner join user on user.user_id = p.thread_creater"
        + " inner join follow_thread on p.thread_id = follow_thread.thread_id"
        + " where p.rank = 1"
        + " and follow_thread.user_id = :user_id"
        + " order by follow_thread.follow_time desc;")
    Iterable<ThreadList> findFollowThread_OrderByRegist(
        @Param("user_id") String user_id
    );

    //フォロースレッドの新着動画順
    @Query("with pickup_movie as ("
        + " select * from ("
        + " select * ,"
        + " case when response.response_id is null then 1 else row_number() over(partition by thread_id order by like_count desc) end as 'rank' "
        + " from thread"
        + " left outer join (select count(*) as follow_count, follow_thread.thread_id from follow_thread group by follow_thread.thread_id) follow_count using(thread_id)"
        + " left outer join (select count(*) as res_count, response.thread_id from response group by response.thread_id) res_count using(thread_id)"
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
        + " , coalesce(p.res_count, 0) as res_count, coalesce(p.follow_count, 0) as follow_count "
        + " from thread " 
        + " inner join user on user.user_id = thread.thread_creater"
        + " inner join jenre on jenre.jenre_id = thread.jenre_id"
        + " inner join follow_thread on follow_thread.thread_id = thread.thread_id"
        + " left outer join pickup_movie as p on p.thread_id = thread.thread_id"
        + " left outer join new_movie on new_movie.thread_id = thread.thread_id"
        + " where follow_thread.user_id = :user_id"
        + " order by new_movie.response_submit desc, thread.thread_submit asc;")
    Iterable<ThreadList> findFollowThread_OrderByUpdate(
        @Param("user_id") String user_id
    );

    //ジャンル別のスレッドの人気順
    @Query("with pop_thread as ("
        + " select * ,"
	    + " row_number() over(partition by temp.jenre_id order by temp.th_num desc) as 'pop_th' "
	    + " from ("
		+ " select * ,"
        + " case when response.response_id is null then 1 else row_number() over(partition by thread_id order by like_count desc) end as 'rank' "
		+ " from thread "
        + " inner join jenre using(jenre_id)"
        + " inner join user on user.user_id = thread.thread_creater"
        + " left outer join (select count(*) as follow_count, follow_thread.thread_id from follow_thread group by follow_thread.thread_id) follow_count using(thread_id)"
        + " left outer join (select count(*) as res_count, response.thread_id from response group by response.thread_id) res_count using(thread_id)"
		+ " left outer join response using(thread_id)"
		+ " left outer join (select response.thread_id, count(*) as th_num from response group by thread_id) as thread_count using(thread_id)"
		+ " left outer join movie using(movie_id)"
        + " left outer join ( select response_creater, response_id, sum(view_like) as 'like_count' from view_response tmp1 group by response_creater, response_id ) like_count using(response_creater, response_id)"
	    + " ) as temp"
	    + " where temp.rank = 1"
        + " )"
        + " select pop.thread_id, pop.thread_name, user.user_id, user.user_name, pop.icon, pop.user_name, pop.thread_submit, pop.response_id, pop.response_name, pop.movie_id, pop.movie_name, coalesce(pop.thumbnail, '/img/のーいめーじ.jpg') as thumbnail, pop.jenre_id, pop.jenre_name"
        + " , coalesce(pop.res_count, 0) as res_count, coalesce(pop.follow_count, 0) as follow_count "
        + " from pop_thread pop"
        + " inner join user on user.user_id = pop.thread_creater"
        + " where pop.pop_th"
        + " and pop.jenre_id = :genre_id"
        + " order by pop_th asc "
        + " ;")
    Iterable<ThreadList> findGenreThread_OrderByPopular(
        @Param("genre_id") String genre_id
    );

    //ジャンル別スレッドの新着順
    @Query("with pickup_movie as ("
        + " select * from ("
        + " select * , case when response.response_id is null then 1 else row_number() over(partition by thread_id order by like_count desc) end as 'rank'"
        + " from thread "
        + " inner join jenre using(jenre_id)"
        + " inner join user on user.user_id = thread.thread_creater "
        + " left outer join (select count(*) as follow_count, follow_thread.thread_id from follow_thread group by follow_thread.thread_id) follow_count using(thread_id)"
        + " left outer join (select count(*) as res_count, response.thread_id from response group by response.thread_id) res_count using(thread_id)"
        + " left outer join response using(thread_id)"
        + " left outer join movie using(movie_id)"
        + " left outer join ( select response_creater, response_id, sum(view_like) as 'like_count' from view_response tmp1 group by response_creater, response_id ) like_count using(response_creater, response_id)"
        + " order by thread.thread_submit desc"
        + " )"
        + "  as temp where temp.rank = 1"
        + " )"

        + " select p.jenre_id, p.jenre_name, user.user_id, user.user_name, user.icon, thread.thread_id, thread.thread_submit, thread.thread_name, thread.thread_creater, p.response_creater, p.response_id, p.response_name, p.movie_id, p.url, coalesce(p.thumbnail, '/img/のーいめーじ.jpg') as thumbnail "
        + " , coalesce(p.res_count, 0) as res_count, coalesce(p.follow_count, 0) as follow_count "
        + " from thread "
        + " inner join user on user.user_id = thread.thread_creater"
        + " left outer join pickup_movie as p on p.thread_id = thread.thread_id"
        + " where thread.jenre_id = :genre_id"
        + " order by thread.thread_submit desc;")
    Iterable<ThreadList> findGenreThread_OrderByRegist(
        @Param("genre_id") String genre_id
    );

    //スレッド検索
    @Query("with pickup_movie as ("
        + " select * from ( select *,"
        + " case when response.response_id is null then 1 else row_number() over(partition by thread_id order by like_count desc) end as 'rank' "
        + " from thread"
        + " inner join jenre using(jenre_id)"
        + " inner join user on thread.thread_creater = user.user_id"
        + " left outer join response using(thread_id)"
        + " left outer join movie using(movie_id)"
        + " left outer join (select count(*) as follow_count, follow_thread.thread_id from follow_thread group by follow_thread.thread_id) follow_count using(thread_id)"
        + " left outer join (select count(*) as res_count, response.thread_id from response group by response.thread_id) res_count using(thread_id)"
        + " left outer join ( select response_creater, response_id, sum(view_like) as 'like_count' from view_response tmp1 group by response_creater, response_id ) like_count using(response_creater, response_id)"
        + " where case when :word != '' then thread_name like concat('%', :word, '%') else 1=0 end"
        + " order by response.response_submit desc"
        + " ) as temp where temp.rank = 1)"
        + " select p.user_id, p.user_name, p.icon, p.thread_id, p.thread_name, p.jenre_name, p.thread_submit, coalesce(p.thumbnail, '/img/のーいめーじ.jpg') as thumbnail, "
        + " coalesce(p.res_count, 0) as res_count, coalesce(p.follow_count, 0) as follow_count from pickUp_movie as p order by p.thread_id, p.rank;")
    Iterable<ThreadList> findSearchThread(
        @Param("word") String search_word
    );
}
