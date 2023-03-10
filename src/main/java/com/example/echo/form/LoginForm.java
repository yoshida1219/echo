package com.example.echo.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    @NotBlank(message = "入力してください")
    private String search_name;

    @NotBlank(message = "入力してください")
    private String pass;
}
