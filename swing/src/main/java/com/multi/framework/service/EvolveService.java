package com.multi.framework.service;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.action.Shape;

public interface EvolveService<E extends Entity, S extends Shape> extends
		AttributeService<E> {

	S evolve(E entity, S currentShape);
}
