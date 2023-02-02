package com.example.echo.service.Thread;

import java.util.Optional;

import com.example.echo.entity.Thread;
import com.example.echo.entity.select.JenreThread;

public interface ThreadService {

    String selectMaxThread_id();
    
    String findThreadJenre_name(String thread_Id);

    void insertThread(Thread thread);

    void deleteThread(String thread_id);
    
    Optional<Thread> selectThread(String thread_id);

    void insertFollowThread(String user_id, String thread_id);

    void deleteFollowThread(String user_id, String thread_id);

    Integer findFollowCheck(String user_id, String thread_id);

    Optional<JenreThread> findJenreThread(String thread_id);

}
