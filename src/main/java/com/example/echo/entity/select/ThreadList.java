package com.example.echo.entity.select;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadList {
    @Id
    private String user_id;

    private String user_name;

    private String thread_id;

    private String thread_name;

    private String jenre_name;

    private String thread_submit;

    private String thumbnail;

    private String icon;

    private Integer res_count;

    private Integer follow_count;
}
