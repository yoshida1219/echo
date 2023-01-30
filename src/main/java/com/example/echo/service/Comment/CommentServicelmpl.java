package com.example.echo.service.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.Comment;
import com.example.echo.repository.CommentRepository;

@Service
@Transactional
public class CommentServicelmpl implements CommentService{
    @Autowired
    CommentRepository repository;
    
    @Override
    public Iterable<Comment>  SelectComment(String user_id,String response_id){
        return repository.SelectComment(user_id,response_id);
    }

    @Override
    public String maxCommentId(String view_user){
        return repository.MaxCommentID(view_user);
    }

    @Override
    public void insertComment(Comment comment){
        repository.insertComment(comment.getResponse_id(),
                                    comment.getView_user(),
                                    comment.getComment_id(),
                                    comment.getComment(),
                                    comment.getResponse_creater());
    }
}
