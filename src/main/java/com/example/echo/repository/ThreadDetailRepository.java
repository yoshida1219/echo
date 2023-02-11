package com.example.echo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.ThreadDetail;
import com.example.echo.entity.select.ThreadList;
import com.example.echo.entity.select.JenreThread;

public interface ThreadDetailRepository extends CrudRepository<ThreadDetail,String>{
    //投稿　いいね順で３つ
    @Query("with like_count as ( select response_creater, response_id, sum(view_like) as 'like_count' from view_response tmp1 group by response_creater, response_id ) "
        + ", share_count as ( select count(ORIGIN_ID) as 'share_count', origin_creater as response_creater, origin_id as response_id from response tmp2 group by origin_creater, origin_id union select tmp3.share_count, response_creater, response_id from response inner join (select count(*) as 'share_count', origin_creater, origin_id from response tmp2 group by origin_creater, origin_id) tmp3 using(origin_creater, origin_id))"
        + "select thread.thread_id, thread.thread_name, response.response_id, response.response_creater, response.response_name, coalesce(like_count, 0) as 'like', coalesce(share_count, 0) as 'share', movie.movie_name, movie.url,coalesce(movie.thumbnail, '/img/のーいめーじ.jpg') as thumbnail,user.user_name, user.icon "
        + " from thread"
        + " inner join response on response.thread_id = thread.thread_id"
        + " inner join movie on movie.movie_id = response.movie_id"
        + " inner join user on user.user_id = response.response_creater"

        + " left outer join like_count on like_count.response_creater = response.response_creater and like_count.response_id = response.response_id"
        + " left outer join share_count on share_count.response_creater = response.response_creater and share_count.response_id = response.response_id"

        + " where thread.thread_id = :thread_id"
        + " order by like_count desc limit 3;")
    Iterable<ThreadDetail> findThreadDetail_Popular3(
        @Param("thread_id") String thread_id
    );

    //投稿　すべて　投稿の日付の降順
    /*
    @Query("with like_count as ( select response_creater, response_id, sum(view_like) as like_count from view_response a group by response_creater, response_id ),"
    + " share_count as( select count(*) as share_count, origin_creater , origin_id from response b group by origin_creater, origin_id)"

    + " select thread.thread_id, thread.thread_name, response.response_id, response.response_creater, response.response_name, response.like, response.share, movie.movie_name, movie.url, coalesce(movie.thumbnail, '/img/のーいめーじ.jpg') as thumbnail, user.user_id, user.user_name, user.icon "
    + " , coalesce(like_count.like_count, 0) as 'a', coalesce(share_count.share_count, 0) as 'b'"
    + " from thread "
    + " inner join response on response.thread_id = thread.thread_id"
    + " inner join movie on movie.movie_id = response.movie_id"
    + " inner join user on user.user_id = response.response_creater"

    + " left outer join share_count using(response_creater, response_id)"
    + " left outer join like_count on like_count.origin_creater = response.response_creater and like_count.origin_id = response.response_id"
    + " where thread.thread_id = 'T000001'"
    + " order by response.response_submit desc;")
    */

     
    @Query("with like_count as ( select response_creater, response_id, sum(view_like) as 'like_count' from view_response tmp1 group by response_creater, response_id ), "
        + "share_count as ( select count(ORIGIN_ID) as 'share_count', origin_creater as response_creater, origin_id as response_id from response tmp2 group by origin_creater, origin_id union select tmp3.share_count, response_creater, response_id from response inner join (select count(*) as 'share_count', origin_creater, origin_id from response tmp2 group by origin_creater, origin_id) tmp3 using(origin_creater, origin_id))"

        + " select thread.thread_id, thread.thread_name, response.response_id, response.response_creater, response.response_name, movie.movie_name, movie.url, coalesce(movie.thumbnail, '/img/のーいめーじ.jpg') as thumbnail, user.user_id, user.user_name, user.icon, jenre.jenre_name "

        + " , coalesce(like_count.like_count, 0) as 'like', coalesce(share_count.share_count, 0) as 'share', row_number() over(order by response.response_submit asc) as 'res_count'"
        
        + " from thread "

        + " inner join jenre on jenre.jenre_id = thread.jenre_id"

        + " inner join response on response.thread_id = thread.thread_id"
        + " inner join movie on movie.movie_id = response.movie_id"
        + " inner join user on user.user_id = response.response_creater"

        + " left outer join like_count on like_count.response_creater = response.response_creater and like_count.response_id = response.response_id"
        + " left outer join share_count on share_count.response_creater = response.response_creater and share_count.response_id = response.response_id"

        + " where thread.thread_id = :thread_id"
        + " order by response.response_submit desc;")
    Iterable<ThreadDetail> findThreadDetailAll(
        @Param("thread_id") String thread_id
    );


    //スレッド検索
    //一番人気以外の６つ
    @Query("with pickup_movie as ("
        + " select * from ( select *,"
        + " row_number() over(partition by response.thread_id order by response.like desc) as 'rank' "
        + " from thread"
        + " left outer join response using(thread_id)"
        + " left outer join movie using(movie_id)"
        + " where case when :word != '' then thread_name like concat('%', :word, '%') else 1=0 end"
        + " order by response.response_submit desc"
        + " ) as temp where temp.rank <= 7 and temp.rank > 1)"
        + " select * from pickUp_movie as p order by p.thread_id, p.rank;")
    Iterable<ThreadDetail> findThreadByWord(
        @Param("word") String word
    );

    //スレッド検索
    //一番人気のみ
    @Query("with pickup_movie as ("
        + " select * from ( select *,"
        + " row_number() over(partition by response.thread_id order by response.like desc) as 'rank' "
        + " from thread"
        + " inner join user on thread.thread_creater = user.user_id"
        + " inner join jenre using(jenre_id)"
        + " left outer join response using(thread_id)"
        + " left outer join movie using(movie_id)"
        + " where case when :word != '' then thread_name like concat('%', :word, '%') else 1=0 end"
        + " order by response.response_submit desc"
        + " ) as temp where temp.rank = 1)"
        + " select user_id, user_name, thread_id, thread_name, jenre_name, thread_submit, icon, coalesce(p.thumbnail, '/img/のーいめーじ.jpg') as thumbnail from pickUp_movie as p order by p.thread_id, p.rank;")
    Iterable<ThreadList> findThreadOneByWord(
        @Param("word") String word
    );

    @Query("""
             select * 
             from thread
             inner join jenre using(jenre_id)
             where thread_id =:thread_id 
            """)
    Optional<JenreThread> OrderJenreThread(
        @Param("thread_id") String thread_id
    );
}