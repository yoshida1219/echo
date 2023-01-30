package com.example.echo.service.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.User;
import com.example.echo.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository repository;

    @Override
    public Optional<User> selectUserOne(String serch_name) {
        return repository.findBySerchName(serch_name);
    }

    @Override
    public void insertUser(User user) {
        repository.insertUser(
            user.getUser_id(),
            user.getUser_name(),
            user.getPassword(),
            user.getMail(),
            user.getSearch_name()
        );
    }

    @Override
    public String selectUserIdMax() {
        return repository.findUserIdMax();
    }

    @Override
    public Optional<String> selectMail(String mail) {
        return repository.findMail(mail);
    }

    @Override
    public Optional<String> selectSearchName(String search_name) {
        return repository.findSearchName(search_name);
    }

    @Override
    public Optional<User> selectMypageUser(String user_id) {
        return repository.findMypageUser(user_id);
    }

    @Override
    public Optional<String> selectUserId(String search_name) {
        return repository.findUserId(search_name);
    }

    @Override
    public Optional<User> selectUser(String user_id) {
        return repository.findById(user_id);
    }

    @Override
    public Iterable<User> selectUserByWord(String search_word) {
        return repository.findUserByWord(search_word);
    }

    @Override
    public void updateIcon(String user_id, String icon) {
        repository.updateIcon(user_id, icon);
        
    }
}
