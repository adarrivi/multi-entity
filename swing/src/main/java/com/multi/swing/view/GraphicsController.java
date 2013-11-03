package com.multi.swing.view;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.multi.swing.entity.Entity;
import com.multi.swing.service.view.ViewEntityService;
import com.multi.swing.service.view.impl.ViewServiceType;

@Component
public class GraphicsController {

	private List<Entity> entities = new ArrayList<>();

	@Autowired
	private ApplicationContext applicationContext;

	public void propagateDrawingAction(Graphics2D graphics2d) {
		drawEntities(graphics2d);
	}

	private void drawEntities(Graphics2D graphics2d) {
		for (Entity entity : entities) {
			Class<ViewEntityService<Entity>> logicServiceClass = ViewServiceType
					.getServiceClassByEntityClass(entity.getClass());
			ViewEntityService<Entity> service = applicationContext
					.getBean(logicServiceClass);
			service.drawEntity(entity, graphics2d);
		}

	}

	public void addAllEntities(Collection<Entity> entitiesToAdd) {
		entities.addAll(entitiesToAdd);
	}

}
