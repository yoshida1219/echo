package com.example.echo.service.FollowThread;

import com.example.echo.entity.select.FollowThread;

public interface FollowThreadService {
    Iterable<FollowThread> OrderFollowThread(String user_id);
}
