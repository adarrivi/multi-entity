package com.multi.framework.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.event.Event;
import com.multi.framework.service.SpeakService;

@Service
public class DefaultSilentService implements SpeakService<Entity, Event> {

	@Override
	public Map<Entity, List<Event>> speak(Entity entity) {
		return Collections.emptyMap();
	}

}
