package com.example.echo.entity.select;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Follower {
    
    @Id
    private Integer follower; 

    private String user_name;

    private String introduction;

    private String icon;
}
