package com.example.echo.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserForm {
    @NotBlank(message = "入力してください")
    @Length(max=20, message = "20文字以内で入力してください")
    private String search_name;

    @NotBlank(message = "入力してください")
    @Length(max=20, message = "20文字以内で入力してください")
    private String user_name;

    @NotBlank(message = "入力してください")
    @Length(max=20, message = "20文字以内で入力してください")
    private String pass;
}
