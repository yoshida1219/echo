package com.example.echo.repository;

import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.select.SubmitResponse;

public interface SubmitResponseRepository extends CrudRepository<SubmitResponseRepository, Integer> {

    @Query("select count(*) as 'submitcount' from response where response_creater = :user_id;" )
        Optional<SubmitResponse> OrderSubmitCount(@Param("user_id") String user_id);
    
}
