package com.example.echo.service.Notice;

import com.example.echo.entity.User;

public interface NoticeService {
    Iterable<User> FindNoticeFollow(String user_id);

    void FindUpdateFollow(String user_id, String login_user_id);
}
