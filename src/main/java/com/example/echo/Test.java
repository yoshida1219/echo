package com.example.echo;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.echo.service.Follower.FollowerService;
import com.example.echo.service.User.UserService;
import com.example.echo.session.SessionData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@Order(1)
public class Test implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {

    }
}
