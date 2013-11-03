package com.multi.swing.service.view.impl;

import java.awt.Graphics2D;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.multi.swing.entity.HormoneEntity;
import com.multi.swing.entity.HormoneTraceEntity;
import com.multi.swing.service.view.ViewEntityService;

@Service
class HormoneTraceViewSerivceDefaultImpl implements
		ViewEntityService<HormoneTraceEntity> {

	@Autowired
	@Qualifier("hormoneViewSerivceDefaultImpl")
	private ViewEntityService<HormoneEntity> hormoneViewSerivce;

	@Override
	public void drawEntity(HormoneTraceEntity hormoneTrace,
			Graphics2D graphics2d) {
		for (HormoneEntity hormone : hormoneTrace.getHormones()) {
			hormoneViewSerivce.drawEntity(hormone, graphics2d);
		}

	}
}
