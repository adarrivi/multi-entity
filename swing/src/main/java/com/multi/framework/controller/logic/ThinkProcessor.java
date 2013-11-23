package com.multi.framework.controller.logic;

import org.springframework.stereotype.Component;

import com.multi.framework.container.InternalEntitiesServiceWrapper;
import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.InternalEntity;
import com.multi.framework.domain.entity.action.State;
import com.multi.framework.service.ThinkService;

@Component("thinkProcessor")
class ThinkProcessor implements ServiceProcessorCallback {

	@Override
	public void processService(InternalEntitiesServiceWrapper serviceWrapper) {
		ThinkService<Entity, State> service = serviceWrapper
				.getAttributeService();
		for (InternalEntity entity : serviceWrapper.getInternalEntities()) {
			State newState = service.changeState(entity.getEntity(),
					entity.getCurrentState());
			entity.setCurrentState(newState);
		}
	}

}
