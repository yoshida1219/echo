package com.example.echo.service.PopularThread;

import com.example.echo.entity.select.PopularThread;

public interface PopularThreadService {
    Iterable<PopularThread> OrderPopularThread(String jenre_id);
}
