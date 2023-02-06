package com.example.echo.service.Follow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.Follow;
import com.example.echo.repository.FollowRepository;

@Service
@Transactional
public class FollowUserServiceImpl implements FollowUserService{

    @Autowired
    FollowRepository Follow;

    @Override
    public Iterable<Follow> selectFollow(String user_id,String session_user_id) {
        return Follow.selectFollow(user_id,session_user_id);
    }

    @Override
    public void insertFollow(String user_id,
                             String followuser_id){
            System.out.println("insert");
            Follow.insertFollow(user_id, followuser_id);
    }

    @Override
    public void deleteFollow(String user_id,
                             String followuser_id){
        System.out.println("delete");
        Follow.deleteFollow(user_id, followuser_id);
    }

    @Override
    public Boolean follow_judgement(String user_id,
                                    String followuser_id){
        System.out.println("judgment");
        int judgment = Follow.follow_judgement(user_id, followuser_id);
        if(judgment > 0){return true;}else{return false;}
    }
}