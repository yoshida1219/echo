package com.example.echo.repository;


import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.Jenre;

public interface JenreRepository extends CrudRepository<Jenre, String> {
    @Query("select jenre_id from join_jenre where user_id = :user_id;")
    Iterable<Jenre> findJenre(@Param("user_id") String user_id);
}

