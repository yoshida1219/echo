package com.example.echo.service.Follow;

import com.example.echo.entity.Follow;

public interface FollowUserService {

    Iterable<Follow> selectFollow(String user_id,String session_user_id);

    void insertFollow(String user_id,
                      String folowuser_id);
    
    void deleteFollow(String user_id,
                      String folowuser_id);

    Boolean follow_judgement(String user_id,
                             String followuser_id);        
}
