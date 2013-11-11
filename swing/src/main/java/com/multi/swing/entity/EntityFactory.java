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
	@Value("${terrain.colony.antsPerColony}")
	private int antsPerColony;
	@Value("${terrain.nuberOfColonies}")
	private int nuberOfColonies;
	@Value("${terrain.numberOfFood}")
	private int numberOfFood;

	@Autowired
	@Qualifier("searchFoodState")
	private AntState searchFoodState;

	public List<PositionEntity> createEcosystem() {
		List<PositionEntity> entities = createAntColonies();
		entities.addAll(createFood());
		return entities;
	}

	private List<PositionEntity> createAntColonies() {
		List<PositionEntity> entities = new ArrayList<>();
		for (int i = 0; i < nuberOfColonies; i++) {
			entities.addAll(createAntColony());
		}
		return entities;
	}

	private List<PositionEntity> createAntColony() {
		List<PositionEntity> entities = new ArrayList<>();
		NestEntity nest = new NestEntity(RandomUtils.getIntance().getPoint(
				terrainWidth, terrainHeight));
		entities.add(nest);
		for (int i = 0; i < antsPerColony; i++) {
			AntEntity ant = new AntEntity(nest.getPosition(), searchFoodState,
					nest);
			entities.add(ant);
			entities.addAll(ant.getTrace());
		}
		return entities;
	}

	private List<PositionEntity> createFood() {
		List<PositionEntity> entities = new ArrayList<>();
		for (int i = 0; i < numberOfFood; i++) {
			entities.add(new FoodEntity(RandomUtils.getIntance().getPoint(
					terrainWidth, terrainHeight)));
		}
		return entities;
	}
}
