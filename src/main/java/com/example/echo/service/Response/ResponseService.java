package com.example.echo.service.Response;

import com.example.echo.entity.Response;

public interface ResponseService {
    String selectMaxResponseId(String user_id);

    void insert(Response response);

    void insertView_Response(Response response);

    void deleteResponse(String response_creater,String response_id);

    Boolean FirstTimeView(Response response);
    
    void ShareResponse(String login_user_id, String login_user_creater, String response_creater, String response_id);

    Integer OrderShare_check(String login_user_id, String response_creater, String response_id);
}
