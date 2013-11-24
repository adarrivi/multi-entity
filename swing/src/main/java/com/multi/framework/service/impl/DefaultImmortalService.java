package com.multi.framework.service.impl;

import org.springframework.stereotype.Service;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.service.SurviveService;

@Service
public class DefaultImmortalService implements SurviveService<Entity> {

    @Override
    public boolean survives(Entity entity) {
        return true;
    }

}
