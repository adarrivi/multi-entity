package com.multi.swing.step;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class StepController extends Observable {

	private static final Logger LOG = LoggerFactory
			.getLogger(StepController.class);
	private StepControllerStatus status = StepControllerStatus.STOPPED;

	@Async
	public void start() {
		status = StepControllerStatus.RUNNING;
		run();
	}

	private void run() {
		while (StepControllerStatus.RUNNING.equals(status)) {
			setChanged();
			LOG.debug("Notifying observers with thread {}",
					Thread.currentThread());
			notifyObservers();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void addAllObservers(Collection<? extends Observer> entities) {
		for (Observer observer : entities) {
			addObserver(observer);
		}
	}

	public void stop() {
		status = StepControllerStatus.STOPPED;
	}

}
