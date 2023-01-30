package com.example.echo.service.Notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.User;
import com.example.echo.repository.NoticeRepository;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeRepository inst;

    public Iterable<User> FindNoticeFollow(String user_id) {
        return inst.OrderNoticeFollow(user_id);
    }

    public void FindUpdateFollow(String user_id, String login_user_id) {
        inst.update(user_id, login_user_id);
    }
    
}
