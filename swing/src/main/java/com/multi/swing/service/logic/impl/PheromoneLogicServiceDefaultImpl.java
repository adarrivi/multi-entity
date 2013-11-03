package com.multi.swing.service.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.swing.controller.logic.EntitiesController;
import com.multi.swing.entity.PheromoneEntity;
import com.multi.swing.service.logic.LogicEntityService;

@Service("pheromoneLogicServiceDefaultImpl")
class PheromoneLogicServiceDefaultImpl implements
		LogicEntityService<PheromoneEntity> {

	private static final float FAINT_DELTA = -0.01f;

	@Autowired
	private EntitiesController entitiesController;

	@Override
	public void stepEntity(PheromoneEntity pheromone) {
		pheromone.incrementIntensity(FAINT_DELTA);
		if (pheromone.hasDissapeared()) {
			entitiesController.removeEntity(pheromone);
		}
	}
}
