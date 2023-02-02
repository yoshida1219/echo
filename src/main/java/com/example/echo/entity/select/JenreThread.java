package com.example.echo.entity.select;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JenreThread {

    @Id
    private String thread_id;
    private String thread_name;
    private String thread_submit;
    private String jenre_id;
    private String jenre_name;
    
}
