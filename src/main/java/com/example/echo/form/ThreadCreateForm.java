package com.example.echo.form;

import org.hibernate.validator.constraints.Length;

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
    @Length(max=50, message="50文字以内で入力してください")
    private String thread_name;

    private String thread_id;

    
}
