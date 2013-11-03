package com.multi.swing.service.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.swing.controller.logic.EntitiesController;
import com.multi.swing.entity.FeromoneEntity;
import com.multi.swing.service.logic.LogicEntityService;

@Service("feromoneLogicServiceDefaultImpl")
class FeromoneLogicServiceDefaultImpl implements
		LogicEntityService<FeromoneEntity> {

	private static final float FAINT_DELTA = -0.01f;

	@Autowired
	private EntitiesController entitiesController;

	@Override
	public void stepEntity(FeromoneEntity feromone) {
		feromone.incrementIntensity(FAINT_DELTA);
		if (feromone.hasDissapeared()) {
			entitiesController.removeEntity(feromone);
		}
	}
}
