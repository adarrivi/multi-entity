package com.multi.framework.service;

import java.util.Collection;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.event.Event;

public interface ListenService<E extends Entity, K extends Event> extends
		AttributeService<E> {

	void hearEvents(E entity, Collection<K> events);

}
