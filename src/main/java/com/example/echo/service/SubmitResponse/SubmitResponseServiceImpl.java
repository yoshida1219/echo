package com.example.echo.service.SubmitResponse;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.echo.entity.select.SubmitResponse;

import com.example.echo.repository.SubmitResponseRepository;

@Service
@Transactional
public class SubmitResponseServiceImpl implements SubmitResponseService {

    @Autowired
    SubmitResponseRepository inst;

    @Override
    public Optional<SubmitResponse> findSubmitResponse(String user_id) {
        return inst.OrderSubmitCount(user_id);
    }
}
