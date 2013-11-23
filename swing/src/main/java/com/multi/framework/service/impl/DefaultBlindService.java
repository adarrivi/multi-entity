package com.multi.framework.service.impl;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.service.SeeService;

@Service
public class DefaultBlindService implements SeeService<Entity> {

	@Override
	public void seeEntities(Entity entity, Collection<Entity> entities) {
		// Nothing to see
	}

}
