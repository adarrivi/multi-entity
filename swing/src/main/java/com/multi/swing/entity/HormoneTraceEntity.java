package com.multi.swing.entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.multi.swing.step.StepController;
import com.multi.swing.view.GraphicsController;

public class HormoneTraceEntity implements Observer {

	private static final Logger LOG = LoggerFactory
			.getLogger(HormoneTraceEntity.class);

	private List<HormoneEntity> trace = new ArrayList<>();
	private Graphics2D graphics2d;

	@Override
	public void update(Observable observable, Object observableArgument) {
		if (observable instanceof GraphicsController) {
			graphics2d = ((Graphics2D) observableArgument);
			drawTrace();
		}
		if (observable instanceof StepController) {
			step();
		}
	}

	private void drawTrace() {
		LOG.debug("Drawing trace with  thread {}", Thread.currentThread());
		for (HormoneEntity hormone : trace) {
			hormone.draw(graphics2d);
		}
	}

	public void extendTrace(Point position) {
		trace.add(new HormoneEntity(position));
	}

	private void step() {
		List<HormoneEntity> toRemove = new ArrayList<>();
		for (HormoneEntity hormone : trace) {
			hormone.step();
			if (hormone.hasDissapeared()) {
				toRemove.add(hormone);
			}
		}
		trace.removeAll(toRemove);
	}
}
