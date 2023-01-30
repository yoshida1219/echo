package com.example.echo.service.Recommend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.User;
import com.example.echo.repository.RecommendRepository;

@Service
@Transactional
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    RecommendRepository repository;

    @Override
    public Iterable<User> FindRecommendUser(String user_id) {
        return repository.OrderRecommendUser(user_id);
    }
}
