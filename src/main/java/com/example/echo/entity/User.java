package com.example.echo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String user_id;

    private String user_name;

    private String search_name;

    private String password;

    private String mail;

    //2023-1-11追加(阿部)
    private String introduction;

    private String icon;
}
