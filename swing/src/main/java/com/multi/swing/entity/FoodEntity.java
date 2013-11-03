package com.multi.swing.entity;

import java.awt.Point;

public class FoodEntity implements PositionEntity {

	private Point position;
	private int amount = 8;

	public FoodEntity(Point position) {
		this.position = new Point(position);
	}

	@Override
	public Point getPosition() {
		return position;
	}

	public int getAmount() {
		return amount;
	}

	public void decreaseAmount() {
		if (isEmpty()) {
			return;
		}
		amount--;
	}

	public boolean isEmpty() {
		return amount == 0;
	}

}
