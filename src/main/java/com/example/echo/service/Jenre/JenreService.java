package com.example.echo.service.Jenre;

import com.example.echo.entity.Jenre;

public interface JenreService {
    Iterable<Jenre> selectAll();

    Iterable<Jenre> findJenre(String user_id);
}
