package com.example.echo.repository;


import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.Jenre;

public interface JenreRepository extends CrudRepository<Jenre, String> {
    @Query("select jenre_id from join_jenre where user_id = :user_id;")
    Iterable<Jenre> findJenre(@Param("user_id") String user_id);

    @Query("SELECT echo_sns.join_jenre.jenre_id, echo_sns.jenre.jenre_name FROM echo_sns.join_jenre inner join echo_sns.jenre on echo_sns.join_jenre.jenre_id = echo_sns.jenre.jenre_id where user_id = :user_id;")
    Iterable<Jenre> findJenreNames(@Param("user_id") String user_id);

    @Query("select * from echo_sns.jenre ;")
    Iterable<Jenre> allJenre();



    @Modifying
    @Query("INSERT INTO echo_sns.join_Jenre VALUES(:user_id, :jenre_id)")
    void insertJenre(
        @Param("user_id") String user_id,
        @Param("jenre_id") String jenre_id
    );

    @Modifying
    @Query("delete from echo_sns.join_jenre where user_id = :user_id;")
    void deleteJenre(
        @Param("user_id") String user_id
    );
}

