package com.multi.framework.example;

import org.springframework.stereotype.Service;

import com.multi.framework.service.SurviveService;

@Service
public class DogSurviveService implements SurviveService<Dog> {

    @Override
    public boolean survives(Dog entity) {
        entity.step();
        return entity.isAlive();
    }

}
