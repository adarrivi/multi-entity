package com.multi.swing.entity;

import java.awt.Point;
import java.util.Random;

public class AntEntity implements Entity {

	private static final Random RANDOM = new Random();

	private Point position;
	private double rotation = RANDOM.nextDouble() * 2 * Math.PI;
	private double steps;

	private FeromoneTraceEntity trace;

	public AntEntity(Point position, FeromoneTraceEntity trace) {
		this.trace = trace;
		this.position = position;
	}

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

	public void extendTrace() {
		trace.extendTrace(position);
	}

	public double getRotation() {
		return rotation;
	}

	public FeromoneTraceEntity getTrace() {
		return trace;
	}

}
