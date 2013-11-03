package com.multi.swing.service.view.impl;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;

import org.springframework.stereotype.Service;

import com.multi.swing.entity.HormoneEntity;
import com.multi.swing.service.view.ViewEntityService;
import com.multi.swing.view.DrawUtils;
import com.multi.swing.view.Images;

@Service("hormoneViewSerivceDefaultImpl")
class HormoneViewSerivceDefaultImpl implements ViewEntityService<HormoneEntity> {

	@Override
	public void drawEntity(HormoneEntity hormone, Graphics2D graphics2d) {
		Composite oldComposite = graphics2d.getComposite();
		int rule = AlphaComposite.SRC_OVER;
		Composite comp = AlphaComposite.getInstance(rule,
				hormone.getIntensity());
		graphics2d.setComposite(comp);
		DrawUtils.getInstance().drawImage(Images.GREEN_HORMONE,
				hormone.getPosition(), graphics2d);
		graphics2d.setComposite(oldComposite);
	}

}
