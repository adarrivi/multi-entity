package com.multi.framework.service;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.action.Position;

public interface MoveService<E extends Entity, P extends Position> extends
		AttributeService<E> {

	P move(E entity, P currentPosition);

}
