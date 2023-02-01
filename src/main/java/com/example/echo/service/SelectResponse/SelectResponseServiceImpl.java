package com.example.echo.service.SelectResponse;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.select.SelectResponse;
import com.example.echo.repository.SelectResponseRepository;

@Service
@Transactional
public class SelectResponseServiceImpl implements SelectResponseService{
    @Autowired
    SelectResponseRepository repository;

    @Override
    public Optional<SelectResponse> SelectResponse(String resposne_id,String user_id){
        return repository.SelectResponse(resposne_id,user_id);
    }

    @Override
    public String SelectThread_id(String response_id,String user_id){
        return repository.SelectThread_id(response_id, user_id);
    }

    @Override
    public String SelectMovie_id(String response_id,String user_id){
        return repository.SelectMovie_id(response_id, user_id);
    }
    
    @Override
    public Iterable<SelectResponse> OrderPopular(){
        return repository.OrderPopularMovie();
    }

    @Override
    public Iterable<SelectResponse> OrderNewThread(String jenre_id){
        return repository.OrderNewThread(jenre_id);
    }
}
