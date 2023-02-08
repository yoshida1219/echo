package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.SelectFollowerMovie;

public interface HomeSelectRepository extends CrudRepository<SelectFollowerMovie, String>{
    // @Query("select user1.user_name as my_name, user2.user_name as follower_name, response.response_name, movie.movie_name, movie.url, movie.thumbnail, thread.thread_name, response.like, response.share from follow"
    //     + " inner join user as user1 on user1.user_id = follow.user_id "
    //     + " inner join user as user2 on user2.user_id = follow.followuser_id "
    //     + " inner join response on response.response_creater = user2.user_id "
    //     + " inner join thread on response.thread_id = thread.thread_id "
    //     + " inner join movie on movie.movie_id = response.movie_id "
    //     + " where user1.user_id = 'U00000002';")
    // Iterable<SelectFollowerMovie> findFollowerMovies();

    @Query("with like_count as ( select response_creater, response_id, sum(view_like) as 'like_count' from view_response tmp1 group by response_creater, response_id ), "
        + "share_count as ( select count(*) as 'share_count', origin_creater as response_creater, origin_id as response_id from response tmp2 group by origin_creater, origin_id) "

        + "select user1.user_name as my_name, user2.user_name as follower_name, user2.icon, " 
        + "response.response_name, response.response_submit, movie.movie_name, movie.url, coalesce(movie.thumbnail, '/img/のーいめーじ.jpg') as thumbnail, "
        + "thread.thread_name, "
        +  "response.response_id,user2.user_id "
        + " , coalesce(like_count.like_count, 0) as 'like', coalesce(share_count.share_count, 0) as 'share' "
        + "from follow "
        + "inner join user as user1 on user1.user_id = follow.user_id "
        + "inner join user as user2 on user2.user_id = follow.followuser_id " 
        + "inner join response on response.response_creater = user2.user_id "
        + "inner join movie on movie.movie_id = response.movie_id "
        + "left outer join thread on response.thread_id = thread.thread_id "


        + "left outer join like_count on like_count.response_creater = response.response_creater and like_count.response_id = response.response_id "
        + "left outer join share_count on share_count.response_creater = response.response_creater and share_count.response_id = response.response_id "


        + "where user1.user_id = :user_id " 

        + "union " 

        + "select user.user_name as my_name, user.user_name as follower_name, user.icon, response.response_name, response.response_submit, movie.movie_name, movie.url, coalesce(movie.thumbnail, '/img/のーいめーじ.jpg') as thumbnail," 
        + "thread.thread_name, response.response_id, user.user_id " 
        + " , coalesce(like_count.like_count, 0) as 'like', coalesce(share_count.share_count, 0) as 'share' "
        + "from user " 
        + "inner join response on response_creater = user.user_id " 
        + "inner join movie on movie.movie_id = response.movie_id " 
        + "left outer join thread on thread.thread_id = response.thread_id "
        
        + "left outer join like_count on like_count.response_creater = response.response_creater and like_count.response_id = response.response_id "
        + "left outer join share_count on share_count.response_creater = response.response_creater and share_count.response_id = response.response_id "
        
        + "where response.response_creater = :user_id " 
        + "order by response_submit desc limit 30"
        + ";")
        /*@Query("select user1.user_name as 'my_name', user2.user_name as 'follower_name', "
        + "response.response_name, movie.movie_name, movie.url, movie.thumbnail, response.like, response.share, user2.user_id, "
        + "thread.thread_name "
        + "from follow "
        + "inner join user as user1 on user1.user_id = follow.user_id "
        + "inner join user as user2 on user2.user_id = follow.followuser_id "
        + "inner join response on response.response_creater = user2.user_id "
        + "inner join thread on response.thread_id = thread.thread_id "
        + "inner join movie on movie.movie_id = response.movie_id "
        + "where user1.user_id = :user_id or user2.user_id = :user_id; ") 
        */
        Iterable<SelectFollowerMovie> findFollowerMovies(@Param("user_id") String user_id);

}
