package com.multi.swing.service.view.impl;

import java.awt.Graphics2D;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.multi.swing.entity.FeromoneEntity;
import com.multi.swing.entity.FeromoneTraceEntity;
import com.multi.swing.service.view.ViewEntityService;

@Service
class FeromoneTraceViewSerivceDefaultImpl implements
		ViewEntityService<FeromoneTraceEntity> {

	@Autowired
	@Qualifier("feromoneViewSerivceDefaultImpl")
	private ViewEntityService<FeromoneEntity> feromoneViewSerivce;

	@Override
	public void drawEntity(FeromoneTraceEntity feromoneTrace,
			Graphics2D graphics2d) {
		for (FeromoneEntity feromone : feromoneTrace.getFeromones()) {
			feromoneViewSerivce.drawEntity(feromone, graphics2d);
		}

	}
}
