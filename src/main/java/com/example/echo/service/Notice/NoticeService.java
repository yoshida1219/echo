package com.example.echo.service.Notice;

import com.example.echo.entity.User;
import com.example.echo.entity.select.Notice;

public interface NoticeService {
    Iterable<Notice> FindNoticeFollow(String user_id);

    void FindUpdateFollow(String user_id, String login_user_id);

    void FindUpdateComment(String login_user_id, String response_id);
}
