package com.example.echo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.User;

public interface UserRepository extends CrudRepository<User, String>{
    @Query("SELECT * FROM user WHERE search_name = :search_name")
    Optional<User> findBySerchName(@Param("search_name") String search_name);

    @Query("SELECT user_id FROM user ORDER BY user_id desc limit 1")
    String findUserIdMax();

    //@Query("SELECT mail FROM user WHERE mail = :mail")
    //Optional<String> findMail(@Param("mail") String mail);

    @Query("SELECT search_name FROM user WHERE search_name = :search_name")
    Optional<String> findSearchName(@Param("search_name") String search_name);

    @Modifying
    @Query("INSERT INTO user VALUES(:user_id, :user_name, :pass, :search_name, null, '/img/icon/user.png', now())")
    void insertUser(
        @Param("user_id") String user_id,
        @Param("user_name") String user_name,
        @Param("pass") String pass,
        @Param("search_name") String search_name
    );

    @Query("select * from user where user_id = :user_id;")
    Optional<User> findMypageUser(@Param("user_id") String user_id);

    @Query("SELECT user_id FROM user WHERE search_name = :search_name;")
    Optional<String> findUserId(@Param("search_name") String search_name);

    @Query("SELECT * FROM user WHERE case when :search_word != '' then user_name like concat('%', :search_word, '%') else 1=0 end"
        + " or case when :search_word != '' then search_name like concat('%', :search_word, '%') else 1=0 end")
    Iterable<User> findUserByWord(@Param("search_word") String search_word);

    @Modifying
    @Query("UPDATE echo_sns.user SET icon = :icon where user_id = :user_id;")
    void updateIcon(
        @Param("user_id") String user_id,
        @Param("icon") String icon
    );
}
