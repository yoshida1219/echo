package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.View_response;

public interface ViewResponseRepository extends CrudRepository<View_response, String>{
    @Modifying
    @Query("INSERT INTO view_response VALUES(:response_id, :response_creater, :view_user, :view_like, now())")
    void insertView_Response(
        @Param("response_id") String response_id,
        @Param("response_creater") String response_creater,
        @Param("view_user") String view_user,
        @Param("view_like") String view_like
    );

    @Modifying
    @Query("UPDATE view_response SET view_like = view_like + :view_like WHERE response_id = :response_id "
        + " AND response_creater = :response_creater"
        + " AND view_user = :view_user"
    )
    void updateLike(
        @Param("response_id") String response_id,
        @Param("response_creater") String response_creater,
        @Param("view_user") String view_user,
        @Param("view_like") String view_like
    );

}
