package com.multi.swing.entity.ant;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import com.multi.swing.entity.FeromoneEntity;
import com.multi.swing.entity.PositionEntity;
import com.multi.swing.util.RandomUtils;

public class AntEntity implements PositionEntity {

	private Point position;
	private double rotation = RandomUtils.getIntance().getRandomRotation();
	private double steps;
	private List<FeromoneEntity> trace = new ArrayList<>();
	private AntState state;

	public AntEntity(Point position, AntState state) {
		this.position = new Point(position);
		this.state = state;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public void increaseRotation(double rotationDelta) {
		this.rotation += rotationDelta;
	}

	public void increaseStep() {
		steps++;
		if (steps == Double.MAX_VALUE) {
			steps = 0;
		}
	}

	public double getSteps() {
		return steps;
	}

	public double getRotation() {
		return rotation;
	}

	public List<FeromoneEntity> getTrace() {
		return trace;
	}

	public void updateTrace() {
		List<FeromoneEntity> toRemove = new ArrayList<>();
		for (FeromoneEntity feromone : trace) {
			if (feromone.hasDissapeared()) {
				toRemove.add(feromone);
			}
		}
		trace.removeAll(toRemove);
	}

	public AntState getState() {
		return state;
	}

	public void setState(AntState state) {
		this.state = state;
	}

}
