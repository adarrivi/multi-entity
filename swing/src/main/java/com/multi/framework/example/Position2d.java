package com.multi.framework.example;

import com.multi.framework.domain.entity.action.Position;

public class Position2d implements Position {

	private double x;
	private double y;
	private double rotation;

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

}
