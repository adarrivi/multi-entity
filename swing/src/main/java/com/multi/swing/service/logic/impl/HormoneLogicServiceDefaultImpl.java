package com.multi.swing.service.logic.impl;

import org.springframework.stereotype.Service;

import com.multi.swing.entity.HormoneEntity;
import com.multi.swing.service.logic.LogicEntityService;

@Service("hormoneLogicServiceDefaultImpl")
class HormoneLogicServiceDefaultImpl implements
		LogicEntityService<HormoneEntity> {

	private static final float FAINT_DELTA = -0.01f;

	@Override
	public void stepEntity(HormoneEntity hormone) {
		hormone.incrementIntensity(FAINT_DELTA);
	}
}
