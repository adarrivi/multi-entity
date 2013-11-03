package com.multi.swing.service.view.impl;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;

import org.springframework.stereotype.Service;

import com.multi.swing.controller.view.DrawUtils;
import com.multi.swing.controller.view.Images;
import com.multi.swing.entity.HormoneEntity;
import com.multi.swing.service.view.ViewEntityService;

@Service("hormoneViewSerivceDefaultImpl")
class HormoneViewSerivceDefaultImpl implements ViewEntityService<HormoneEntity> {

	@Override
	public void drawEntity(HormoneEntity hormone, Graphics2D graphics2d) {
		Composite oldComposite = graphics2d.getComposite();
		int rule = AlphaComposite.SRC_OVER;
		Composite comp = AlphaComposite.getInstance(rule,
				hormone.getIntensity());
		graphics2d.setComposite(comp);
		// graphics2d.setColor(Color.GREEN);
		// graphics2d.drawRect(hormone.getPosition().x, hormone.getPosition().y,
		// 5, 5);
		DrawUtils.getInstance().drawImage(Images.GREEN_HORMONE,
				hormone.getPosition(), graphics2d);
		graphics2d.setComposite(oldComposite);
	}

}
