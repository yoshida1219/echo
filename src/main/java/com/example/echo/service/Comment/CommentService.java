package com.example.echo.service.Comment;

import com.example.echo.entity.Comment;

public interface CommentService {

    Iterable<Comment> SelectComment(String user_id,String response_id);     

    String maxCommentId(String view_user);

    void insertComment(Comment comment);
}
