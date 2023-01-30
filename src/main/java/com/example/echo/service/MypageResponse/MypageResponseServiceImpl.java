package com.example.echo.service.MypageResponse;

import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.select.MypageResponse;
import com.example.echo.repository.MypageResponseRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MypageResponseServiceImpl implements MypageResponseService {
    
    @Autowired
    MypageResponseRespository inst;

    @Override
    public Iterable<MypageResponse> orderMypageResponse(String user_id) {
        return inst.findMypageResponse(user_id);
    }
}
