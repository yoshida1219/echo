package com.example.echo.entity.select;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteMovie {
    
    @Id
    private String movie_id;

    private String user_id;

    private String movie_name;
    
    private String url;

    private String thumbnail;
}
