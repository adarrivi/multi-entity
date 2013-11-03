package com.multi.swing.entity;

import java.awt.Point;

public class PheromoneEntity implements PositionEntity {

	private float intensity = 1;
	private final Point position;
	private PheromonType type;

	public PheromoneEntity(Point position, PheromonType type) {
		this.position = new Point(position);
		this.type = type;
	}

	public boolean hasDissapeared() {
		return intensity <= 0;
	}

	public void incrementIntensity(float intensityDelta) {
		this.intensity += intensityDelta;
	}

	public float getIntensity() {
		return intensity;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	public PheromonType getType() {
		return type;
	}
}
