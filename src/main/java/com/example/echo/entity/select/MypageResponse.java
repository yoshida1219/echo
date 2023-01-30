package com.example.echo.entity.select;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MypageResponse {

    @Id
    private String user_id;

    private String response_id;

    private String response_name;

    private String response_submit;

    private String thread_id;

    private String thread_name;

    private String movie_id;

    private String movie_name;

    private String thumbnail;

    private String url;
}
