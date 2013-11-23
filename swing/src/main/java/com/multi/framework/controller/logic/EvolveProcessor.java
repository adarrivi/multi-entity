package com.multi.framework.controller.logic;

import org.springframework.stereotype.Component;

import com.multi.framework.container.InternalEntitiesServiceWrapper;
import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.InternalEntity;
import com.multi.framework.domain.entity.action.Shape;
import com.multi.framework.service.EvolveService;

@Component("evolveProcessor")
class EvolveProcessor implements ServiceProcessorCallback {

	@Override
	public void processService(InternalEntitiesServiceWrapper serviceWrapper) {
		EvolveService<Entity, Shape> service = serviceWrapper
				.getAttributeService();
		for (InternalEntity entity : serviceWrapper.getInternalEntities()) {
			Shape newShape = service.evolve(entity.getEntity(),
					entity.getCurrentShape());
			entity.setCurrentShape(newShape);
		}
	}
}
