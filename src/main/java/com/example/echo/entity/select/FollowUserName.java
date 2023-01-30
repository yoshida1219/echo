package com.example.echo.entity.select;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FollowUserName {
    private String user_name;

    private String user_name_follow;
}
