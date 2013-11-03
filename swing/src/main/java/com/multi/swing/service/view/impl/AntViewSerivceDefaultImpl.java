package com.multi.swing.service.view.impl;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.swing.entity.ant.AntEntity;
import com.multi.swing.service.view.ViewEntityService;
import com.multi.swing.util.DrawUtils;

@Service
class AntViewSerivceDefaultImpl implements ViewEntityService<AntEntity> {

	@Autowired
	private ImageCache imageCache;

	@Override
	public void drawEntity(AntEntity ant, Graphics2D graphics2d) {
		AffineTransform oldTransform = graphics2d.getTransform();
		Point position = ant.getPosition();
		AffineTransform rotator = AffineTransform.getRotateInstance(
				ant.getRotation(), position.x, position.y);
		graphics2d.setTransform(rotator);
		DrawUtils.getInstance().drawImage(imageCache.ANT, position, graphics2d);
		// graphics2d.setColor(Color.BLUE);
		// graphics2d.drawRect(position.x, position.y, 20, 20);
		graphics2d.setTransform(oldTransform);

	}
}
