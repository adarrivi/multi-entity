package com.multi.swing.service.view.impl;

import java.awt.Graphics2D;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.swing.entity.FoodEntity;
import com.multi.swing.service.view.ViewEntityService;
import com.multi.swing.util.DrawUtils;

@Service
class FoodViewSerivceDefaultImpl implements ViewEntityService<FoodEntity> {

	@Autowired
	private ImageCache imageCache;

	@Override
	public void drawEntity(FoodEntity food, Graphics2D graphics2d) {
		DrawUtils.getInstance().drawImage(
				imageCache.FOOD_ARRAY[food.getAmount()], food.getPosition(),
				graphics2d);
	}
}
