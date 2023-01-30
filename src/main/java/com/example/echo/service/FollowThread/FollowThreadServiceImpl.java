package com.example.echo.service.FollowThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.select.FollowThread;
import com.example.echo.repository.FollowThreadRepository;

@Service
@Transactional
public class FollowThreadServiceImpl implements FollowThreadService{
    @Autowired
    FollowThreadRepository repository;

    @Override
    public Iterable<FollowThread> OrderFollowThread(String user_id){
        return repository.OrderFollowThread(user_id);
    }
    
}
