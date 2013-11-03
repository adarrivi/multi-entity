package com.multi.swing.controller.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	private static final long FPS_25_NANO = TimeUnit.MILLISECONDS.toNanos(60);
	private List<Entity> entities = new ArrayList<>();

	@Value("${msPerFrame}")
	private int msPerFrame;

	@Autowired
	private Terrain terrain;

	private long elapsedTimeNano;

	@Autowired
	private ApplicationContext applicationContext;

	@Async
	public void start() {
		long currentTime = System.nanoTime();
		if (elapsedTimeNano != 0) {
			sleepIfTooFast();
		}
		elapsedTimeNano = currentTime;
		run();
	}

	private void sleepIfTooFast() {
		long currentTime = System.nanoTime();
		long nanosPerFrame = TimeUnit.MILLISECONDS.toNanos(msPerFrame);
		long toSleepMs = TimeUnit.NANOSECONDS.toMillis(nanosPerFrame
				- (currentTime - elapsedTimeNano));
		if (toSleepMs > 0) {
			try {
				Thread.sleep(toSleepMs);
			} catch (InterruptedException e) {
				// Nothing to do
			}
		}
	}

	private void run() {
		stepEntities();
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
