package com.multi.swing.entity.ant;

import java.awt.Point;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.multi.swing.controller.logic.EntitiesController;
import com.multi.swing.entity.FoodEntity;
import com.multi.swing.entity.PheromonType;
import com.multi.swing.entity.PheromoneEntity;
import com.multi.swing.util.RandomUtils;

@Component("searchFoodState")
public class SearchFoodState implements AntState {

	private static final int MARK_EACH_STEPS = 5;
	private static final double ROTATION_DELTA = 0.1;
	private static final int FORWARD_STEP = 5;

	@Value("${terrain.height}")
	private int height;
	@Value("${terrain.width}")
	private int width;

	private int imageStateIndex = 0;

	@Autowired
	private EntitiesController entitiesController;
	@Autowired
	@Qualifier("foodFoundState")
	private AntState foodFoundState;

	@Override
	public void doAction(AntEntity ant) {
		rotateAntRandomly(ant);
		moveForward(ant);
		updateTrace(ant);
		imageStateIndex = (imageStateIndex + 1) % 1;
	}

	private void rotateAntRandomly(AntEntity ant) {
		boolean rotateClockWise = RandomUtils.getIntance().getBoolean();
		double rotationDelta = rotateClockWise ? ROTATION_DELTA
				: -ROTATION_DELTA;
		ant.increaseRotation(rotationDelta);
	}

	private void moveForward(AntEntity ant) {
		Point nextPosition = getNextPosition(ant);

		if (isPositionOutOfBounds(nextPosition)) {
			turnAround(ant);
			return;
		}
		ant.setPosition(nextPosition);
		ant.increaseStep();
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
		ant.increaseRotation(RandomUtils.getIntance().getDouble(Math.PI / 2));
	}

	private void updateTrace(AntEntity ant) {
		ant.updateTrace();
		if (ant.getSteps() % MARK_EACH_STEPS == 0) {
			PheromoneEntity pheromoneEntity = new PheromoneEntity(
					ant.getPosition(), PheromonType.EXPLORE);
			ant.getTrace().add(pheromoneEntity);
			entitiesController.addEntity(pheromoneEntity);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public AntState getNextState(AntEntity ant) {
		List<FoodEntity> foodInPositionList = (List<FoodEntity>) entitiesController
				.getEntitiesInPosition(FoodEntity.class, ant.getPosition(), 20);
		if (foodInPositionList.isEmpty() || foodInPositionList.get(0).isEmpty()) {
			return this;
		}
		foodInPositionList.get(0).decreaseAmount();
		return foodFoundState;
	}

	@Override
	public int getStateIndex() {
		return imageStateIndex;
	}
}
