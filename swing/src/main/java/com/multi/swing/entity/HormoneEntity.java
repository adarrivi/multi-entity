package com.multi.swing.entity;

import java.awt.Point;

public class HormoneEntity implements Entity {

	private float intensity = 1;
	private final Point position;

	public HormoneEntity(Point position) {
		this.position = new Point(position);
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

	public Point getPosition() {
		return position;
	}

}
