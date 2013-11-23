package com.multi.framework.controller.logic;

import org.springframework.stereotype.Component;

import com.multi.framework.container.InternalEntitiesServiceWrapper;
import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.InternalEntity;
import com.multi.framework.service.SeeService;

@Component("seeProcessor")
class SeeProcessor implements ServiceProcessorCallback {

	@Override
	public void processService(InternalEntitiesServiceWrapper serviceWrapper) {
		SeeService<Entity> service = serviceWrapper.getAttributeService();
		for (InternalEntity entity : serviceWrapper.getInternalEntities()) {
			service.seeEntities(entity.getEntity(), entity.getVisibleEntities());
			entity.clearVisibleEntities();
		}
	}

}
