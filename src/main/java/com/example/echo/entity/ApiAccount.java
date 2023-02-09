package com.example.echo.entity;

import java.sql.Timestamp;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiAccount {
    @Id
    private String id;

    private boolean flag;

    private Timestamp url;

    private int quota;
}
