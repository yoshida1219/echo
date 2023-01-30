package com.example.echo.entity.select;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadDetail {
    @Id
    private String thread_id;
    private String thread_name;
    private String response_id;
    private String response_name;
    private String response_creater;
    private String response_submit;
    private String like;
    private String share;
    private String movie_name;
    private String url;
    private String thumbnail;
    private String user_id;
    private String user_name;
    private String icon;
}
