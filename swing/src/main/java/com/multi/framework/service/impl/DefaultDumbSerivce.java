package com.multi.framework.service.impl;

import org.springframework.stereotype.Service;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.action.State;
import com.multi.framework.service.ThinkService;

@Service
public class DefaultDumbSerivce implements ThinkService<Entity, State> {

	@Override
	public State changeState(Entity entity, State state) {
		return state;
	}

}
