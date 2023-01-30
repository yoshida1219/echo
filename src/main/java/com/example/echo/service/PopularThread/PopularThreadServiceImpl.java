package com.example.echo.service.PopularThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.select.PopularThread;
import com.example.echo.repository.PopularThreadRepository;

@Service
@Transactional
public class PopularThreadServiceImpl implements PopularThreadService{
    @Autowired
    PopularThreadRepository repository;
    
    @Override
    public Iterable<PopularThread> OrderPopularThread(String jenre_id){
        return repository.OrderPopularThread(jenre_id);
    }
}
