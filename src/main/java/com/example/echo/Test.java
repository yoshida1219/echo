package com.example.echo;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.echo.service.Follower.FollowerService;
import com.example.echo.service.User.UserService;
import com.example.echo.session.SessionData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class Test implements CommandLineRunner{

    @Autowired
    FollowerService inst;

    @Autowired
    UserService userService;

    @Autowired
    SessionData sessionData;

    @Override
    public void run(String... args) throws Exception {
        //sessionData.setUser_id("U00000002");
    }
}
