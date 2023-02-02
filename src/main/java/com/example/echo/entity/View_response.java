package com.example.echo.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class View_response {
    @Id
    private String response_id;

    private String response_creater;

    private String view_user;

    private String view_like;

    private Date view_time;
}
