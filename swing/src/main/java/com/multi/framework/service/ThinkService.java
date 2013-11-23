package com.multi.framework.service;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.action.State;

public interface ThinkService<E extends Entity, S extends State> extends
		AttributeService<E> {

	S changeState(E entity, S state);

}
