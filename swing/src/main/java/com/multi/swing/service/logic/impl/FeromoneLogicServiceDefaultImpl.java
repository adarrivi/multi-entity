package com.multi.swing.service.logic.impl;

import org.springframework.stereotype.Service;

import com.multi.swing.entity.FeromoneEntity;
import com.multi.swing.service.logic.LogicEntityService;

@Service("feromoneLogicServiceDefaultImpl")
class FeromoneLogicServiceDefaultImpl implements
		LogicEntityService<FeromoneEntity> {

	private static final float FAINT_DELTA = -0.01f;

	@Override
	public void stepEntity(FeromoneEntity feromone) {
		feromone.incrementIntensity(FAINT_DELTA);
	}
}
