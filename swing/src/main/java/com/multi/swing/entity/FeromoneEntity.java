package com.multi.swing.entity;

import java.awt.Point;

public class FeromoneEntity implements PositionEntity {

	private float intensity = 1;
	private final Point position;
	private FeromonType type;

	public FeromoneEntity(Point position, FeromonType type) {
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

	public FeromonType getType() {
		return type;
	}
}
