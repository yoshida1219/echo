package com.example.echo.service.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.Response;
import com.example.echo.repository.ResponseRepository;

@Service
@Transactional
public class ResponseServiceImpl implements ResponseService{
    @Autowired
    ResponseRepository repository;
    
    @Override
    public String selectMaxResponseId(String user_id) {
        return repository.findMaxResponseId(user_id);
    }

    @Override
    public void insert(Response response) {
        repository.insert(response.getResponse_creater(), response.getResponse_id(), response.getResponse_name(), response.getThread_id(), response.getMovie_id());
    }

    @Override
    public void insertView_Response(Response response){
        repository.insertView_Response(response.getResponse_id(), response.getResponse_creater(), response.getView_user());
    }

    @Override
    public void deleteResponse(String response_creater,String response_id){
        repository.deleteResponse(response_creater, response_id);
    }

    @Override
    public Boolean FirstTimeView(Response response){
        int viewCount = Integer.parseInt(repository.FirstTimeView(response.getResponse_id(), response.getResponse_creater(), response.getView_user())); 
        if(viewCount >= 1){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public void ShareResponse(String login_user_id, String login_user_response, String response_creater, String response_id) {
        repository.ShareInsert(login_user_id, login_user_response, response_creater, response_id);
    }

    @Override
    public Integer OrderShare_check(String login_user_id, String response_creater, String response_id) {
        return repository.findCheck_share(login_user_id, response_creater, response_id);
    }

    @Override
    public void DeleteShareResponse(String login_user_id, String response_creater, String response_id) {
        repository.deleteShareResponse(login_user_id, response_creater, response_id);
    }

    @Override
    public Integer ThreadResponseCount(String thread_id) {
        return repository.findResponseCount(thread_id);
    }
}