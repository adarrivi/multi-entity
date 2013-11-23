package com.multi.framework.service.impl;

import org.springframework.stereotype.Service;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.action.Shape;
import com.multi.framework.service.EvolveService;

@Service
public class DefaultImmutableService implements EvolveService<Entity, Shape> {

	@Override
	public Shape evolve(Entity entity, Shape currentShape) {
		return currentShape;
	}

}
