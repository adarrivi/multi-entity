package com.multi.swing.step;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

	private static final int RE_PROCESS_DELAY_MS = 50;

	private StepControllerStatus status = StepControllerStatus.STOPPED;
	private List<Entity> entities = new ArrayList<>();

	@Autowired
	private Terrain terrain;

	@Autowired
	private ApplicationContext applicationContext;

	@Async
	public void start() {
		status = StepControllerStatus.RUNNING;
		run();
	}

	private void run() {
		while (StepControllerStatus.RUNNING.equals(status)) {
			stepEntities();
			terrain.repaint();
			sleep();
		}
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

	private void sleep() {
		try {
			Thread.sleep(RE_PROCESS_DELAY_MS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addAllEntities(Collection<Entity> entitiesToAdd) {
		entities.addAll(entitiesToAdd);
	}

	public void stop() {
		status = StepControllerStatus.STOPPED;
	}

}
