package com.multi.swing.service.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.multi.swing.entity.HormoneEntity;
import com.multi.swing.entity.HormoneTraceEntity;
import com.multi.swing.service.logic.LogicEntityService;

@Service
class HormoneTraceLogicServiceDefaultImpl implements
		LogicEntityService<HormoneTraceEntity> {

	@Autowired
	@Qualifier("hormoneLogicServiceDefaultImpl")
	private LogicEntityService<HormoneEntity> hormoneLogicService;

	@Override
	public void stepEntity(HormoneTraceEntity hormoneTrace) {
		List<HormoneEntity> toRemove = new ArrayList<>();
		for (HormoneEntity hormone : hormoneTrace.getHormones()) {
			hormoneLogicService.stepEntity(hormone);
			if (hormone.hasDissapeared()) {
				toRemove.add(hormone);
			}
		}
		hormoneTrace.getHormones().removeAll(toRemove);
	}

}
