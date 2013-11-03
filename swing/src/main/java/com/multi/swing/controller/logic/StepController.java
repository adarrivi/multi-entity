package com.multi.swing.controller.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.multi.swing.Terrain;
import com.multi.swing.entity.Entity;
import com.multi.swing.service.logic.LogicEntityService;
import com.multi.swing.service.logic.impl.LogicServiceType;

@Component
public class StepController {

	private static final Logger LOG = LoggerFactory
			.getLogger(StepController.class);
	private List<Entity> entities = new ArrayList<>();

	@Autowired
	private Terrain terrain;

	@Autowired
	private ApplicationContext applicationContext;

	@Async
	public void start() {
		run();
	}

	private void run() {
		long nanoTime = System.nanoTime();
		stepEntities();
		LOG.debug("Took {}ms. to process logic",
				TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime));
		terrain.repaint();
	}

	private void stepEntities() {
		for (Entity entity : entities) {
			Class<LogicEntityService<Entity>> logicServiceClass = LogicServiceType
					.getServiceClassByEntityClass(entity.getClass());
			LogicEntityService<Entity> logicService = applicationContext
					.getBean(logicServiceClass);
			logicService.stepEntity(entity);
		}
	}

	public void addAllEntities(Collection<Entity> entitiesToAdd) {
		entities.addAll(entitiesToAdd);
	}

}
