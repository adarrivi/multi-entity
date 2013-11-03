package com.multi.swing.controller.logic;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.collect.Iterables;
import com.multi.swing.entity.NestEntity;
import com.multi.swing.entity.PositionEntity;

@Component
public class EntitiesController {

	private Map<Class<? extends PositionEntity>, List<? extends PositionEntity>> entityMap = new HashMap<>();

	public void addAllEntities(Collection<? extends PositionEntity> entities) {
		for (PositionEntity entity : entities) {
			addEntity(entity);
		}
	}

	public List<? extends PositionEntity> getEntitiesInPosition(
			Class<? extends PositionEntity> entityClass, Point position,
			int radius) {
		List<PositionEntity> entitiesInPosition = new ArrayList<>();
		List<? extends PositionEntity> entities = entityMap.get(entityClass);
		if (Iterables.isEmpty(entities)) {
			return entitiesInPosition;
		}

		for (PositionEntity entity : entities) {
			if (position.distance(entity.getPosition()) < radius) {
				entitiesInPosition.add(entity);
			}
		}
		return entitiesInPosition;
	}

	@SuppressWarnings("unchecked")
	public void removeEntity(PositionEntity entity) {
		List<PositionEntity> list = (List<PositionEntity>) entityMap.get(entity
				.getClass());
		if (Iterables.isEmpty(list)) {
			return;
		}
		list.remove(entity);
		entityMap.put(entity.getClass(), list);
	}

	@SuppressWarnings("unchecked")
	public void addEntity(PositionEntity entity) {
		List<PositionEntity> list = (List<PositionEntity>) entityMap.get(entity
				.getClass());
		if (list == null) {
			list = new ArrayList<>();
		}
		list.add(entity);
		entityMap.put(entity.getClass(), list);
	}

	public List<? extends PositionEntity> getAllEntities() {
		Collection<List<? extends PositionEntity>> entitiesLists = entityMap
				.values();
		List<PositionEntity> allEntities = new ArrayList<>();
		for (List<? extends PositionEntity> entitiesList : entitiesLists) {
			allEntities.addAll(entitiesList);
		}
		return allEntities;
	}

	public NestEntity getNest() {
		return (NestEntity) Iterables.getFirst(entityMap.get(NestEntity.class),
				null);
	}

}
