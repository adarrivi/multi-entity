package com.multi.swing.entity;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class AntEntity implements Observer, Entity {

	private static final Random RANDOM = new Random();

	private Point position;
	private double rotation = RANDOM.nextDouble() * 2 * Math.PI;

	private HormoneTraceEntity trace;

	public AntEntity(Point position, HormoneTraceEntity trace) {
		this.trace = trace;
		this.position = position;
	}

	@Override
	public void update(Observable observable, Object observableArgument) {
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

	public void extendTrace() {
		trace.extendTrace(position);
	}

	public double getRotation() {
		return rotation;
	}

	public HormoneTraceEntity getTrace() {
		return trace;
	}

}
