package com.multi.swing.service.view.impl;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import org.springframework.stereotype.Service;

import com.multi.swing.controller.view.DrawUtils;
import com.multi.swing.controller.view.Images;
import com.multi.swing.entity.AntEntity;
import com.multi.swing.service.view.ViewEntityService;

@Service
class AntViewSerivceDefaultImpl implements ViewEntityService<AntEntity> {

	@Override
	public void drawEntity(AntEntity ant, Graphics2D graphics2d) {
		AffineTransform oldTransform = graphics2d.getTransform();
		Point position = ant.getPosition();
		AffineTransform rotator = AffineTransform.getRotateInstance(
				ant.getRotation(), position.x, position.y);
		graphics2d.setTransform(rotator);
		DrawUtils.getInstance().drawImage(Images.ANT, position, graphics2d);
		// graphics2d.setColor(Color.BLUE);
		// graphics2d.drawRect(position.x, position.y, 20, 20);
		graphics2d.setTransform(oldTransform);

	}
}
