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
    public Iterable<Jenre> findJenre(String user_id){
        return repository.findJenre(user_id);
    }
}
