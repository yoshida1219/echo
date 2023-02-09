package com.example.echo.service.ApiAccount;

import com.example.echo.entity.ApiAccount;

public interface ApiAccountService {
    String useAccount();

    void updateFlag();

    void changeFalseFlag(String id);

    void updateQuata(String id, int quata);
}
