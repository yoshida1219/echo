package com.example.echo.entity.select;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyThread {
    @Id
    private String thread_id;

    private String thread_name;

    private String response_submit;

    private String url;

    private String thumbnail;
}
