package com.example.echo.service.ApiAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.ApiAccount;
import com.example.echo.repository.ApiAccountRepository;


@Service
@Transactional
public class ApiAccountServiceImpl implements ApiAccountService{
    @Autowired
    ApiAccountRepository repository;

    @Override
    public String useAccount() {
        return repository.useAccount();
    }

    @Override
    public String getAcQuata(String id) {
        return repository.getAcQuata(id);
    }

    @Override
    public void updateFlag() {
        repository.updateFlag();
    }

    @Override
    public void changeFalseFlag(String id) {
        repository.changeFalseFlag(id);
    }

    @Override
    public void updateQuata(String id, int quata) {
        repository.updateQuata(id, quata);
    }
}
