package com.multi.framework.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.multi.framework.service.MoveService;
import com.multi.swing.util.RandomUtils;

@Service
public class DogMoveService implements MoveService<Dog, Position2d> {

	private static final double ROTATION_DELTA = 0.05;
	private static final int FORWARD_STEP = 5;

	private static Position2d INIT = new Position2d();

	@PostConstruct
	private void init() {
		INIT.setX(RandomUtils.getIntance().getDouble(width));
		INIT.setY(RandomUtils.getIntance().getDouble(height));
	}

	@Value("${terrain.height}")
	private int height;
	@Value("${terrain.width}")
	private int width;

	@Override
	public Position2d move(Dog entity, Position2d currentPosition) {
		if (currentPosition == null) {
			currentPosition = new Position2d();
			currentPosition.setRotation(RandomUtils.getIntance().getDouble(
					Math.PI * 2));
			currentPosition.setX(INIT.getX());
			currentPosition.setY(INIT.getY());
		}
		rotateAntRandomly(currentPosition);
		moveForward(currentPosition);
		return currentPosition;
	}

	private void rotateAntRandomly(Position2d position) {
		boolean rotateClockWise = RandomUtils.getIntance().getBoolean();
		double rotationDelta = rotateClockWise ? ROTATION_DELTA
				: -ROTATION_DELTA;
		position.setRotation(position.getRotation() + rotationDelta);
	}

	private void moveForward(Position2d position) {
		Position2d nextPosition = getNextPosition(position);

		if (isPositionOutOfBounds(nextPosition)) {
			turnAround(nextPosition);
			return;
		}
	}

	private void turnAround(Position2d position) {
		position.setRotation(position.getRotation()
				+ RandomUtils.getIntance().getDouble(Math.PI / 2));
	}

	private Position2d getNextPosition(Position2d position) {
		double rotation = position.getRotation();
		position.setY(position.getY()
				- Double.valueOf(Math.cos(rotation) * FORWARD_STEP));
		position.setX(position.getX()
				+ Double.valueOf(Math.sin(rotation) * FORWARD_STEP));
		return position;
	}

	private boolean isPositionOutOfBounds(Position2d position) {
		return isOutOfRange(position.getX(), width)
				|| isOutOfRange(position.getY(), height);
	}

	private boolean isOutOfRange(double value, double upperLimit) {
		return value < 0 || value > upperLimit;
	}

}
