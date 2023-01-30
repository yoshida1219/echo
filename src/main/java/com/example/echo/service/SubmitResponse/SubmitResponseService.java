package com.example.echo.service.SubmitResponse;

import java.util.Optional;

import com.example.echo.entity.select.SubmitResponse;

public interface SubmitResponseService {

    Optional<SubmitResponse> findSubmitResponse(String user_id);
    
}
