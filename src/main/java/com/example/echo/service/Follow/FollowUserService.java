package com.example.echo.service.Follow;

import java.util.concurrent.CompletableFuture;

import com.example.echo.entity.Follow;

public interface FollowUserService {

    Iterable<Follow> selectFollow(String user_id);

    void insertFollow(String user_id,
                      String folowuser_id);
    
    void deleteFollow(String user_id,
                      String folowuser_id);

    Boolean follow_judgement(String user_id,
                             String followuser_id);        
}
