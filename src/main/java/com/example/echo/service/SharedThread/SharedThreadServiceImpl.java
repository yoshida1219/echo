package com.example.echo.service.SharedThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.select.SharedThread;
import com.example.echo.entity.Response;
import com.example.echo.repository.SharedThreadRepository;

@Service
@Transactional
public class SharedThreadServiceImpl implements SharedThreadService{
    @Autowired
    SharedThreadRepository repository;

    @Override
    public Iterable<SharedThread> selectThread(Response threadList){
        return repository.selectThread(
                                       threadList.getMovie_id(),
                                       threadList.getThread_id()
                                       );
    }

    
}
