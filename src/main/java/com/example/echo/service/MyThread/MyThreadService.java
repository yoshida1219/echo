package com.example.echo.service.MyThread;

import com.example.echo.entity.select.MyThread;

public interface MyThreadService {
    Iterable<MyThread> OrderMyThread(String user_id);
}
