package com.multi.swing.entity.ant;

import java.awt.Point;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.multi.swing.controller.logic.EntitiesController;
import com.multi.swing.entity.NestEntity;
import com.multi.swing.entity.PheromonType;
import com.multi.swing.entity.PheromoneEntity;

@Component("foodFoundState")
public class FoodFoundState implements AntState {

	private static final int MARK_EACH_STEPS = 5;
	private static final int FORWARD_STEP = 5;

	@Value("${terrain.height}")
	private int height;
	@Value("${terrain.width}")
	private int width;

	@Autowired
	private EntitiesController entitiesController;

	@Autowired
	@Qualifier("searchFoodState")
	private AntState searchFoodState;

	@Override
	public void doAction(AntEntity ant) {
		rotateToNest(ant);
		moveForward(ant);
		updateTrace(ant);
	}

	private void rotateToNest(AntEntity ant) {
		NestEntity nest = entitiesController.getNest();
		double distance = nest.getPosition().distance(ant.getPosition());

		double sin = (nest.getPosition().x - ant.getPosition().x) / distance;
		double rotation = Math.asin(sin);

		if (nest.getPosition().y > ant.getPosition().y) {
			rotation = Math.toRadians(180) - rotation;
		}
		ant.setRotation(rotation);
	}

	private void moveForward(AntEntity ant) {
		Point nextPosition = getNextPosition(ant);
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

	private void updateTrace(AntEntity ant) {
		ant.updateTrace();
		if (ant.getSteps() % MARK_EACH_STEPS == 0) {
			PheromoneEntity pheromoneEntity = new PheromoneEntity(
					ant.getPosition(), PheromonType.FOOD_FOUND);
			ant.getTrace().add(pheromoneEntity);
			entitiesController.addEntity(pheromoneEntity);
		}
	}

	@Override
	public AntState getNextState(AntEntity ant) {
		if (entitiesController.getNest().getPosition()
				.distance(ant.getPosition()) < 20) {
			return searchFoodState;
		}
		return this;
	}

}
