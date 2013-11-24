package com.multi.framework.controller.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multi.framework.container.EntitiesContiner;
import com.multi.framework.container.InternalEntitiesServiceWrapper;
import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.InternalEntity;
import com.multi.framework.service.SurviveService;

@Component("surviveProcessor")
class SurviveProcessor implements ServiceProcessorCallback {

    @Autowired
    private EntitiesContiner entitiesContiner;

    @Override
    public void processService(InternalEntitiesServiceWrapper serviceWrapper) {
        SurviveService<Entity> service = serviceWrapper.getAttributeService();
        List<InternalEntity> toRemove = new ArrayList<>();
        for (InternalEntity entity : serviceWrapper.getInternalEntities()) {
            boolean survive = service.survives(entity.getEntity());
            if (!survive) {
                toRemove.add(entity);
            }
        }
        for (InternalEntity entity : toRemove) {
            entitiesContiner.removeEntity(entity);
        }
    }
}
