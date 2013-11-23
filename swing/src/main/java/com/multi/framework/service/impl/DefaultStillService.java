package com.multi.framework.service.impl;

import org.springframework.stereotype.Service;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.action.Position;
import com.multi.framework.service.MoveService;

@Service
public class DefaultStillService implements MoveService<Entity, Position> {

	@Override
	public Position move(Entity entity, Position currentPosition) {
		return currentPosition;
	}

}
