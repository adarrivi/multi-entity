package com.multi.swing.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.multi.swing.entity.ant.AntEntity;
import com.multi.swing.entity.ant.AntState;
import com.multi.swing.util.RandomUtils;

@Component
public class EntityFactory {

	@Value("${terrain.height}")
	private int terrainHeight;
	@Value("${terrain.width}")
	private int terrainWidth;
	@Value("${colony.numberOfAnts}")
	private int numberOfAnts;

	@Autowired
	@Qualifier("searchFoodState")
	private AntState searchFoodState;

	public List<PositionEntity> createAntColony() {
		List<PositionEntity> entities = new ArrayList<>();

		entities.add(new FoodEntity(RandomUtils.getIntance().getPoint(
				terrainWidth, terrainHeight)));

		entities.add(new FoodEntity(RandomUtils.getIntance().getPoint(
				terrainWidth, terrainHeight)));

		entities.add(new FoodEntity(RandomUtils.getIntance().getPoint(
				terrainWidth, terrainHeight)));

		entities.add(new FoodEntity(RandomUtils.getIntance().getPoint(
				terrainWidth, terrainHeight)));

		entities.add(new FoodEntity(RandomUtils.getIntance().getPoint(
				terrainWidth, terrainHeight)));

		entities.add(new FoodEntity(RandomUtils.getIntance().getPoint(
				terrainWidth, terrainHeight)));

		NestEntity nest = new NestEntity(RandomUtils.getIntance().getPoint(
				terrainWidth, terrainHeight));
		entities.add(nest);
		for (int i = 0; i < numberOfAnts; i++) {
			AntEntity ant = new AntEntity(nest.getPosition(), searchFoodState);
			entities.add(ant);
			entities.addAll(ant.getTrace());
		}
		return entities;
	}

}
