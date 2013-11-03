package com.multi.swing.entity;

import java.awt.Point;

public class NestEntity implements PositionEntity {

	private Point position;

	public NestEntity(Point position) {
		this.position = new Point(position);
	}

	@Override
	public Point getPosition() {
		return position;
	}
}
