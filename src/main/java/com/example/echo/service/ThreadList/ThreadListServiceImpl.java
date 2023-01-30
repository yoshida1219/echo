package com.example.echo.service.ThreadList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.select.ThreadList;
import com.example.echo.repository.ThreadListRepository;

@Service
@Transactional
public class ThreadListServiceImpl implements ThreadListService {
    @Autowired
    ThreadListRepository repository;

    @Override
    public Iterable<ThreadList> selectMyThread_OrderByRegist(String user_id) {
        return repository.findMyThread_OrderByRegist(user_id);
    }

    @Override
    public Iterable<ThreadList> selectMyThread_OrderByUpdate(String user_id) {
        return repository.findMyThread_OrderByUpdate(user_id);
    }

    @Override
    public Iterable<ThreadList> selectFollowThread_OrderByRegist(String user_id) {
        return repository.findFollowThread_OrderByRegist(user_id);
    }

    @Override
    public Iterable<ThreadList> selectFollowThread_OrderByUpdate(String user_id) {
        return repository.findFollowThread_OrderByUpdate(user_id);
    }

    @Override
    public Iterable<ThreadList> selectGenreThread_OrderByPopular(String genre_id) {
        return repository.findGenreThread_OrderByPopular(genre_id);
    }

    @Override
    public Iterable<ThreadList> selectGenreThread_OrderByRegist(String genre_id) {
        return repository.findGenreThread_OrderByRegist(genre_id);
    }

    @Override
    public Iterable<ThreadList> selectSearchThread(String search_word) {
        return repository.findSearchThread(search_word);
    }

}
