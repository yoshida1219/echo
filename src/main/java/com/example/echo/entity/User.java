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

    //private String mail;

    //2023-1-11追加(阿部)
    private String introduction;

    //2023-1-31追加(阿部)
    private String open_date;

    //2023-02-12追加(阿部)
    private String open_date_format;

    private String icon;
}
