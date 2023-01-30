package com.example.echo.service.MyThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.select.MyThread;
import com.example.echo.repository.MyThreadRepository;

@Service
@Transactional
public class MyThreadServiceImpl implements MyThreadService{
    @Autowired
    MyThreadRepository repository;

    @Override
    public Iterable<MyThread> OrderMyThread(String user_id){
        return repository.OrderMyThread(user_id);
    }
}
