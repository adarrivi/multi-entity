package com.multi.swing.service.view.impl;

import java.awt.Graphics2D;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.swing.entity.NestEntity;
import com.multi.swing.service.view.ViewEntityService;
import com.multi.swing.util.DrawUtils;

@Service
class NestViewSerivceDefaultImpl implements ViewEntityService<NestEntity> {

	@Autowired
	private ImageCache imageCache;

	@Override
	public void drawEntity(NestEntity nest, Graphics2D graphics2d) {
		DrawUtils.getInstance().drawImage(imageCache.NEST, nest.getPosition(),
				graphics2d);
	}
}
