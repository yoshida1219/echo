package com.example.echo.DB_conf;

public class DBWrite{

    public String getDB(String request) {
        String db = "";

        if(request.equals("CommentRepository")) {
            
        //CommentRepositoryでのDB
        //コメントID、コメントしたユーザー、コメント
        db = "select comment.comment_id, comment_user.user_name, comment.comment, comment.submit_time "
         + " from response "
         + " inner join movie on movie.movie_id = response.movie_id "
         + " inner join view_response on view_response.response_id = response.response_id and view_response.response_creater = response.response_creater "
         + " inner join comment on comment.response_id = view_response.response_id and comment.view_user = view_response.view_user and comment.response_creater = view_response.response_creater "
         + " inner join user as response_user on response_user.user_id = response.response_creater"
         + " inner join user as view_user on view_user.user_id = view_response.view_user"
         + " inner join user as comment_user on comment_user.user_id = comment.view_user"
         + " where response.response_id = 'R000001';";

        }
        
        
        return db;
    } 
    
}