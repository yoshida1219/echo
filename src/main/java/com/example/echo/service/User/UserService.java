package com.example.echo.service.User;

import java.util.Optional;

import com.example.echo.entity.User;

public interface UserService {
    Optional<User> selectUserOne(String serch_name);

    void insertUser(User user);

    String selectUserIdMax();

    Optional<String> selectSearchName(String search_name);

    Optional<User> selectMypageUser(String user_id);

    Optional<String> selectUserId(String search_name);

    Optional<User> selectUser(String user_id);

    Iterable<User> selectUserByWord(String search_word);

    void updateIcon(String user_id, String icon);
}
