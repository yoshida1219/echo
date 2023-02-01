package com.example.echo.service.Jenre;

import com.example.echo.entity.Jenre;

public interface JenreService {
    Iterable<Jenre> selectAll();

    Iterable<Jenre> findJenre(String user_id);

    Iterable<Jenre> findJenreNames(String user_id);

    Iterable<Jenre> allJenre();

    void insertJenre(String user_id, String jenre_id);

    void deleteJenre(String user_id);
}
