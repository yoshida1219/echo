package com.example.echo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    private String comment_id;

    private String comment;

    private String response_id;

    private String view_user;

    private String user_name;

    private String response_creater;

    private String submit_time;

}
