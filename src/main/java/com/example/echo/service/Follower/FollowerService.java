package com.example.echo.service.Follower;

import java.util.Optional;

import com.example.echo.entity.select.Follower;

public interface FollowerService {
    Optional<Follower> OrderFollower(String user_id);

    Optional<Follower> OrderFollowerPeople(String user_id);

    void FollowInsert(String user_id, String follow_user_id);
    
    void FollowDelete(String user_id, String follow_user_id);

    Optional<Integer> FindCheckFollow(String user_id, String follow_user_id);

}
