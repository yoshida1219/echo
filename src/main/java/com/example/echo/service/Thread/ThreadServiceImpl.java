package com.example.echo.service.Thread;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.Thread;
import com.example.echo.repository.ThreadRepository;

@Service
@Transactional
public class ThreadServiceImpl implements ThreadService {
    @Autowired
    ThreadRepository repository;

    @Override
    public String selectMaxThread_id() {
        return repository.findMaxThread_id();
    }

    @Override
    public String findThreadJenre_name(String thread_id){
        return repository.findThreadJenre_name(thread_id);
        }    

    @Override
    public void insertThread(Thread thread){
        repository.insertThread(thread.getThread_id(), 
                                thread.getThread_name(), 
                                thread.getThread_creater(),
                                thread.getJenre_id());
    }

    @Override
    public void deleteThread(String thread_id){
        repository.deleteThread(thread_id);
    }

    @Override
    public Optional<Thread> selectThread(String thread_id) {
        return repository.findById(thread_id);
    }

    @Override
    public void insertFollowThread(String user_id, String thread_id) {
        repository.insertFollowThread(user_id, thread_id);
    }


    @Override
    public void deleteFollowThread(String user_id, String thread_id) {
        repository.deleteFollowThread(user_id, thread_id);
    }


    @Override
    public Integer findFollowCheck(String user_id, String thread_id) {
        return repository.followCheck(user_id, thread_id);
    }
}
