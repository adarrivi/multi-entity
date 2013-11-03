package com.multi.swing.service.view;

import java.awt.Graphics2D;

import com.multi.swing.entity.PositionEntity;

public interface ViewEntityService<T extends PositionEntity> {

	void drawEntity(T entity, Graphics2D graphics2d);

}
