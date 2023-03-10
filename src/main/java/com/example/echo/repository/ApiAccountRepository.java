package com.example.echo.repository;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.echo.entity.ApiAccount;
import com.google.api.client.repackaged.com.google.common.base.Optional;

public  interface ApiAccountRepository extends CrudRepository<ApiAccount,String>{
    @Query("select id FROM echo_sns.api_account where flag = true limit 1;")
    String useAccount();

    @Query("select quata FROM echo_sns.api_account where id = :id;")
    String getAcQuata(
        @Param("id") String id
    );

    @Modifying
    @Query("update echo_sns.api_account set flag = 1 , last_date = now(), quata = 0 where date(last_date) <> date(now()) and id in('A01','A02','A03','A04','A05');")
    void updateFlag();

    @Modifying
    @Query("update echo_sns.api_account set flag = 0 where id = :id")
    void changeFalseFlag(
        @Param("id") String id
    );

    @Modifying
    @Query("update echo_sns.api_account set quata = :quata where id = :id;")
    void updateQuata(
        @Param("id") String id,
        @Param("quata") int quata
    );

}
