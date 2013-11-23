package com.multi.framework.controller.logic;

import org.springframework.stereotype.Component;

import com.multi.framework.container.InternalEntitiesServiceWrapper;
import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.InternalEntity;
import com.multi.framework.domain.entity.action.Position;
import com.multi.framework.service.MoveService;

@Component("moveProcessor")
class MoveProcessor implements ServiceProcessorCallback {

	@Override
	public void processService(InternalEntitiesServiceWrapper serviceWrapper) {
		MoveService<Entity, Position> service = serviceWrapper
				.getAttributeService();
		for (InternalEntity entity : serviceWrapper.getInternalEntities()) {
			Position newPosition = service.move(entity.getEntity(),
					entity.getCurrentPosition());
			entity.setCurrentPosition(newPosition);
		}
	}

}
