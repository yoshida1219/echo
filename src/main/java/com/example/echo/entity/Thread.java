package com.example.echo.entity;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thread {
    @Id
    private String thread_id;

    private String thread_name;

    private String thread_creater;

    private String jenre_id;

    private String thread_submit;
}
