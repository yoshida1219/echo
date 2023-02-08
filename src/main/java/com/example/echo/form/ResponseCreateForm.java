package com.example.echo.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCreateForm {
    @NotBlank(message = "入力してください")
    @Length(min=11, message="11文字以上入力してください")
    private String url;

    @NotBlank(message = "入力してください")
    @Length(max=200, message="200文字以内で入力してください")
    private String response_name;

    private String thread_id;

    private String user_id;

    public void setUrl(int i, int j) {
    }
}
