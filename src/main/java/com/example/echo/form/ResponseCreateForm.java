package com.example.echo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCreateForm {
    @NotBlank(message = "入力してください")
    private String url;

    @NotBlank(message = "入力してください")
    private String response_name;

    private String thread_id;

    private String user_id;

    public void setUrl(int i, int j) {
    }
}
