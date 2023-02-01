package com.example.echo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follow {
    @Id
    private String user_name;

    private String introduction;

    private String icon;
}
