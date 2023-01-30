package com.example.echo.service.MypageResponse;

import org.springframework.stereotype.Component;

import com.example.echo.entity.select.MypageResponse;

@Component
public interface MypageResponseService {
    Iterable<MypageResponse> orderMypageResponse(String user_id);
    
}
