package com.example.echo.service.SelectResponse;

import java.util.Optional;

import com.example.echo.entity.select.SelectResponse;

public interface SelectResponseService {
    Optional<SelectResponse> SelectResponse(String response_id,String user_id);

    String SelectThread_id(String response_id,String user_id);

    String SelectMovie_id(String response_id,String user_id);

    Iterable<SelectResponse> OrderPopular();

    Iterable<SelectResponse> OrderNewThread(String jenre_id);
}
