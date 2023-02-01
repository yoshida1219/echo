package com.example.echo.service.Follow;

import com.example.echo.entity.Follow;

public interface FollowUserService {

    Iterable<Follow> selectFollow(String user_id);
    
}
