package com.multi.framework.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.event.Event;
import com.multi.framework.service.ListenService;

@Service
public class DefaultDeafService implements ListenService<Entity, Event> {

	@Override
	public void hearEvents(Entity entity, Collection<Event> events) {
		// Nothing to do.
	}

}
