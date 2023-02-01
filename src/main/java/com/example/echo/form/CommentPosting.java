package com.example.echo.form;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentPosting {
    private String user_name;

    private String comment;

    private String submit_time;
}