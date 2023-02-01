package com.example.echo.service.Jenre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.echo.entity.Jenre;
import com.example.echo.repository.JenreRepository;

@Service
@Transactional
public class JenreServiceImpl implements JenreService {
    @Autowired
    JenreRepository repository;

    @Override
    public Iterable<Jenre> selectAll() {
        return repository.findAll();
    }

    @Override
    public Iterable<Jenre> findJenreNames(String user_id) {
        return repository.findJenreNames(user_id);
    }

    @Override
    public Iterable<Jenre> findJenre(String user_id){
        return repository.findJenre(user_id);
    }

    @Override
    public Iterable<Jenre> allJenre() {
        return repository.allJenre();
    }

    @Override
    public void insertJenre(String user_id, String jenre_id) {
        repository.insertJenre(user_id, jenre_id);
    }


    @Override
    public void deleteJenre(String user_id) {
        repository.deleteJenre(user_id);
    }
}
