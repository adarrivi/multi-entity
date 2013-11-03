package com.multi.swing.service.view.impl;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.swing.entity.FeromoneEntity;
import com.multi.swing.service.view.ViewEntityService;
import com.multi.swing.util.DrawUtils;

@Service("feromoneViewSerivceDefaultImpl")
class FeromoneViewSerivceDefaultImpl implements
		ViewEntityService<FeromoneEntity> {

	@Autowired
	private ImageCache imageCache;

	@Override
	public void drawEntity(FeromoneEntity feromone, Graphics2D graphics2d) {
		Composite oldComposite = graphics2d.getComposite();
		int rule = AlphaComposite.SRC_OVER;
		Composite comp = AlphaComposite.getInstance(rule,
				feromone.getIntensity());
		graphics2d.setComposite(comp);
		// graphics2d.setColor(Color.GREEN);
		// graphics2d.drawRect(feromone.getPosition().x,
		// feromone.getPosition().y,
		// 5, 5);
		DrawUtils.getInstance().drawImage(
				imageCache.getFeromoneImage(feromone.getType()),
				feromone.getPosition(), graphics2d);
		graphics2d.setComposite(oldComposite);
	}

}
