package com.multi.swing.controller.logic;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.multi.swing.Terrain;
import com.multi.swing.entity.PositionEntity;
import com.multi.swing.service.logic.LogicEntityService;
import com.multi.swing.service.logic.impl.LogicServiceType;

@Component
public class StepController {

	private double steps;

	@Value("${frame.msPerFrame}")
	private int msPerFrame;

	@Value("${frame.skipFrames}")
	private int skipFrames;

	@Autowired
	private Terrain terrain;
	@Autowired
	private EntitiesController entitiesController;

	private long elapsedTimeNano;

	@Autowired
	private ApplicationContext applicationContext;

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
		steps++;
		stepEntities();
		if (skipFrames == 0 || steps % skipFrames == 0) {
			terrain.repaint();
		} else {
			start();
		}
	}

	private void stepEntities() {
		for (PositionEntity entity : entitiesController.getAllEntities()) {
			Class<LogicEntityService<PositionEntity>> logicServiceClass = LogicServiceType
					.getServiceClassByEntityClass(entity.getClass());
			LogicEntityService<PositionEntity> logicService = applicationContext
					.getBean(logicServiceClass);
			logicService.stepEntity(entity);
		}
	}
}
