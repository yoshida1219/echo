package com.example.echo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThreadCreateForm {
    @NotBlank(message = "入力してください")
    private String genre_id;

    @NotBlank(message = "入力してください")
    private String url;

    @NotBlank(message = "入力してください")
    private String thread_name;

    private String thread_id;

    
}
