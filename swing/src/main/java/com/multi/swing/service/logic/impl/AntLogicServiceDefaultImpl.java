package com.multi.swing.service.logic.impl;

import java.awt.Point;
import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.multi.swing.entity.AntEntity;
import com.multi.swing.service.logic.LogicEntityService;

@Service
class AntLogicServiceDefaultImpl implements LogicEntityService<AntEntity> {

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
		ant.extendTrace();
		Point position = new Point(ant.getPosition());
		double rotation = ant.getRotation();
		position.y -= Double.valueOf(Math.cos(rotation) * FORWARD_STEP)
				.intValue();
		position.x += Double.valueOf(Math.sin(rotation) * FORWARD_STEP)
				.intValue();

		if (!isWithinRange(position.x, width)
				|| !isWithinRange(position.y, height)) {
			turnAround(ant);
		}
		ant.setPosition(position);
	}

	private boolean isWithinRange(int value, int upperLimit) {
		return value >= 0 && value <= upperLimit;
	}

	private void turnAround(AntEntity ant) {
		ant.increaseRotation(Math.PI / 2);

	}
}
