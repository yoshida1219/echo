package com.example.echo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    @Id
    private String response_creater;

    private String response_id;

    private String response_name;

    private String thread_id;

    private String movie_id;

    private String like;

    private String share;

    private String response_submit;

    private String view_user;
}
