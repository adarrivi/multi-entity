package com.multi.swing.service.view;

import java.awt.Graphics2D;

import com.multi.swing.entity.Entity;

public interface ViewEntityService<T extends Entity> {

	void drawEntity(T entity, Graphics2D graphics2d);

}
