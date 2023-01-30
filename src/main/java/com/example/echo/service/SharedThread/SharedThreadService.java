package com.example.echo.service.SharedThread;

import com.example.echo.entity.Response;
import com.example.echo.entity.select.SharedThread;

public interface SharedThreadService {
    
    Iterable<SharedThread> selectThread(Response threadList);

}
