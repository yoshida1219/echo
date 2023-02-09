package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.Comment;

public interface CommentRepository extends CrudRepository<Comment,String>{
    @Query("""
        select comment.comment_id, comment_user.user_name, comment.comment, DATE_FORMAT(comment.submit_time, '%Y/%m/%d %H:%i') as submit_time, comment_user.icon, comment_user.user_id
         from response
         inner join movie on movie.movie_id = response.movie_id 
         inner join view_response on view_response.response_id = response.response_id and view_response.response_creater = response.response_creater 
         inner join comment on comment.response_id = view_response.response_id and comment.view_user = view_response.view_user and comment.response_creater = view_response.response_creater 
         inner join user as response_user on response_user.user_id = response.response_creater
         inner join user as view_user on view_user.user_id = view_response.view_user
         inner join user as comment_user on comment_user.user_id = comment.view_user
         where response.response_id = :response_id
         and response.response_creater = :user_id
         order by comment.submit_time desc;
        """
    )
    Iterable<Comment>  SelectComment(
        @Param("user_id") String user_id,
        @Param("response_id") String response_id
    );

    @Query("select comment_id from comment where view_user = :view_user order by comment_id desc limit 1;")
    String MaxCommentID(
        @Param("view_user") String view_user
    );
    
    @Modifying
    @Query("insert into comment value(:response_id,:view_user,:comment_id,:comment,now(),:response_creater, 1G)")
    void insertComment(
            @Param("response_id") String response_id,
            @Param("view_user") String view_user,
            @Param("comment_id") String comment_id,
            @Param("comment") String comment,
            @Param("response_creater") String response_creater
    );

}
