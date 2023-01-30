package com.example.echo.service.View_response;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.echo.entity.View_response;
import com.example.echo.repository.ViewResponseRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class View_responseServiceImpl implements View_responseService{
    @Autowired
    ViewResponseRepository repository;

    @Override
    public void insertView_Response(View_response view_response) {
        repository.insertView_Response(view_response.getResponse_id(), view_response.getResponse_creater(), view_response.getView_user(), view_response.getView_like());
    }

    @Override
    public void updateLike(View_response view_response) {
        repository.updateLike(view_response.getResponse_id(), view_response.getResponse_creater(), view_response.getView_user(), view_response.getView_like());
    }
}
