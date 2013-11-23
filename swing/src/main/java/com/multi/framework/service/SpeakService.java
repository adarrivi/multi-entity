package com.multi.framework.service;

import java.util.List;
import java.util.Map;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.event.Event;

public interface SpeakService<E extends Entity, K extends Event> extends
		AttributeService<E> {

	Map<E, List<K>> speak(E entity);

}
