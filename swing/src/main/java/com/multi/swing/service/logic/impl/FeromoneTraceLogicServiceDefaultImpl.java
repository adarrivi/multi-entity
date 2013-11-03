package com.multi.swing.service.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.multi.swing.entity.FeromoneEntity;
import com.multi.swing.entity.FeromoneTraceEntity;
import com.multi.swing.service.logic.LogicEntityService;

@Service
class FeromoneTraceLogicServiceDefaultImpl implements
		LogicEntityService<FeromoneTraceEntity> {

	@Autowired
	@Qualifier("feromoneLogicServiceDefaultImpl")
	private LogicEntityService<FeromoneEntity> feromoneLogicService;

	@Override
	public void stepEntity(FeromoneTraceEntity feromoneTrace) {
		List<FeromoneEntity> toRemove = new ArrayList<>();
		for (FeromoneEntity feromone : feromoneTrace.getFeromones()) {
			feromoneLogicService.stepEntity(feromone);
			if (feromone.hasDissapeared()) {
				toRemove.add(feromone);
			}
		}
		feromoneTrace.getFeromones().removeAll(toRemove);
	}

}
