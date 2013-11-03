package com.multi.swing.service.logic.impl;

import java.awt.Point;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.multi.swing.entity.AntEntity;
import com.multi.swing.service.logic.LogicEntityService;

@Service
class AntLogicServiceDefaultImpl implements LogicEntityService<AntEntity> {

	private static final int MARK_EACH_STEPS = 5;
	private static final double ROTATION_DELTA = 0.1;
	private static final int FORWARD_STEP = 5;
	private static final Random RANDOM = new Random();

	@Value("${terrain.height}")
	private int height;
	@Value("${terrain.width}")
	private int width;

	@Override
	public void stepEntity(AntEntity ant) {
		rotateAntRandomly(ant);
		moveForward(ant);
	}

	private void rotateAntRandomly(AntEntity ant) {
		boolean rotateClockWise = RANDOM.nextBoolean();
		double rotationDelta = rotateClockWise ? ROTATION_DELTA
				: -ROTATION_DELTA;
		ant.increaseRotation(rotationDelta);
	}

	private void moveForward(AntEntity ant) {
		extendTrace(ant);
		Point nextPosition = getNextPosition(ant);

		if (isPositionOutOfBounds(nextPosition)) {
			turnAround(ant);
			return;
		}
		ant.setPosition(nextPosition);
		ant.increaseStep();
	}

	private void extendTrace(AntEntity ant) {
		if (ant.getSteps() % MARK_EACH_STEPS == 0) {
			ant.extendTrace();
		}
	}

	private Point getNextPosition(AntEntity ant) {
		Point position = new Point(ant.getPosition());
		double rotation = ant.getRotation();
		position.y -= Double.valueOf(Math.cos(rotation) * FORWARD_STEP)
				.intValue();
		position.x += Double.valueOf(Math.sin(rotation) * FORWARD_STEP)
				.intValue();
		return position;
	}

	private boolean isPositionOutOfBounds(Point position) {
		return isOutOfRange(position.x, width)
				|| isOutOfRange(position.y, height);
	}

	private boolean isOutOfRange(int value, int upperLimit) {
		return value < 0 || value > upperLimit;
	}

	private void turnAround(AntEntity ant) {
		ant.increaseRotation(RANDOM.nextDouble() * (Math.PI / 2));

	}
}
