package com.example.echo.service.ApiAccount;



public interface ApiAccountService {
    String useAccount();

    String getAcQuata(String id); 

    void updateFlag();

    void changeFalseFlag(String id);

    void updateQuata(String id, int quata);
}
