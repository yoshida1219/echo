package com.example.echo.service.ThreadDetail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.select.ThreadDetail;
import com.example.echo.entity.select.ThreadList;
import com.example.echo.repository.ThreadDetailRepository;

@Service
@Transactional
public class ThreadDetailServiceImpl implements ThreadDetailService {
    @Autowired
    ThreadDetailRepository repository;

    @Override
    public Iterable<ThreadDetail> selectThreadDetail_Popular3(String thread_id) {
        return repository.findThreadDetail_Popular3(thread_id);
    }

    @Override
    public Iterable<ThreadDetail> selectThreadDetailAll(String thread_id) {
        return repository.findThreadDetailAll(thread_id);
    }

    @Override
    public Iterable<ThreadList> selectThreadOneByWord(String search_word) {
        return repository.findThreadOneByWord(search_word);
    }

}
