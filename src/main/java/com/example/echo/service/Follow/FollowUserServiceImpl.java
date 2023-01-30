package com.example.echo.service.Follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.Follow;
import com.example.echo.repository.FollowRepository;

@Service
@Transactional
public class FollowUserServiceImpl implements FollowUserService{

    @Autowired
    FollowRepository Follow;

    @Override
    public Iterable<Follow> selectFollow(String user_id) {
        return Follow.selectFollow(user_id);
    }
}