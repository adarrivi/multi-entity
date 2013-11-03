package com.multi.swing.controller.view;

import java.awt.Graphics2D;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.multi.swing.controller.logic.EntitiesController;
import com.multi.swing.entity.PositionEntity;
import com.multi.swing.service.view.ViewEntityService;
import com.multi.swing.service.view.impl.ViewServiceType;

@Component
public class GraphicsController {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private EntitiesController entitiesController;

	public void propagateDrawingAction(Graphics2D graphics2d) {
		drawEntities(graphics2d);
	}

	private void drawEntities(Graphics2D graphics2d) {
		for (PositionEntity entity : entitiesController.getAllEntities()) {
			Class<ViewEntityService<PositionEntity>> logicServiceClass = ViewServiceType
					.getServiceClassByEntityClass(entity.getClass());
			ViewEntityService<PositionEntity> service = applicationContext
					.getBean(logicServiceClass);
			service.drawEntity(entity, graphics2d);
		}

	}
}
