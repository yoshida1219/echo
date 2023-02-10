package com.example.echo.entity.select;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notice {

    @Id
    private String user_id;

    private String user_name;

    private String search_name;

    private String icon;

    private String reason;

    private String response_id;

    private String url;
}
