package com.multi.framework.controller.logic;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.multi.framework.container.InternalEntitiesServiceWrapper;
import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.InternalEntity;
import com.multi.framework.domain.event.Event;
import com.multi.framework.service.SpeakService;

@Component("speakProcessor")
class SpeakProcessor implements ServiceProcessorCallback {

	@Override
	public void processService(InternalEntitiesServiceWrapper serviceWrapper) {
		SpeakService<Entity, Event> service = serviceWrapper
				.getAttributeService();
		for (InternalEntity entity : serviceWrapper.getInternalEntities()) {
			Map<Entity, List<Event>> wordMap = service
					.speak(entity.getEntity());
			for (Entry<Entity, List<Event>> word : wordMap.entrySet()) {
				// TODO FINISH IMPLEMENTATION }
			}
		}
	}
}
