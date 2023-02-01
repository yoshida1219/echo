package com.example.echo.entity.select;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectResponse {

    public static String Date_format(String date)throws DateTimeParseException {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy年mm月dd日");
        String change_date = date.formatted(format);
        return change_date;

    }

    @Id
    private String response_id;

    private String user_id;

    private String icon;

    private String response_name;

    private String response_submit;

    private String response_creater;

    private String user_name;

    private String thread_id;

    private String thread_name;

    private String movie_id;

    private String movie_name;

    private String thumbnail;

    private String like;

    private String share;

    private String url;

    private Integer count;

    private String jenre_name;


}
