package com.multi.swing.step;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class StepController extends Observable {

	private StepControllerStatus status = StepControllerStatus.STOPPED;

	@Async
	public void start() {
		status = StepControllerStatus.RUNNING;
		run();
	}

	private void run() {
		while (StepControllerStatus.RUNNING.equals(status)) {
			setChanged();
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
