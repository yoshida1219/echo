package com.example.echo.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreateForm {
    private String comment;

    private String response_id;

    private String response_creater;
    
}
