package com.multi.framework.controller.logic;

import org.springframework.stereotype.Component;

import com.multi.framework.container.InternalEntitiesServiceWrapper;
import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.InternalEntity;
import com.multi.framework.domain.event.Event;
import com.multi.framework.service.ListenService;

@Component("listenProcessor")
class ListenProcessor implements ServiceProcessorCallback {

	@Override
	public void processService(InternalEntitiesServiceWrapper serviceWrapper) {
		ListenService<Entity, Event> service = serviceWrapper
				.getAttributeService();
		for (InternalEntity entity : serviceWrapper.getInternalEntities()) {
			service.hearEvents(entity.getEntity(), entity.getListeningEvents());
			entity.clearListeningEvents();
		}

	}

}
