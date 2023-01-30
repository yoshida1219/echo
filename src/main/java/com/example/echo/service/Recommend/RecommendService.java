package com.example.echo.service.Recommend;

import com.example.echo.entity.User;

public interface RecommendService {
    Iterable<User> FindRecommendUser(String user_id);
}
