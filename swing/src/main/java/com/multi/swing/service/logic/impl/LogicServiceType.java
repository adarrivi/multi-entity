package com.multi.swing.service.logic.impl;

import com.multi.swing.entity.PositionEntity;
import com.multi.swing.entity.PheromoneEntity;
import com.multi.swing.entity.FoodEntity;
import com.multi.swing.entity.NestEntity;
import com.multi.swing.entity.ant.AntEntity;
import com.multi.swing.exception.ConfigurationException;
import com.multi.swing.service.logic.LogicEntityService;

public enum LogicServiceType {

	ANT(AntEntity.class, AntLogicServiceDefaultImpl.class), //
	NET(NestEntity.class, NestLogicServiceDefaultImpl.class), //
	PHEROMONE(PheromoneEntity.class, PheromoneLogicServiceDefaultImpl.class), //
	FOOD(FoodEntity.class, FoodLogicServiceDefaultImpl.class);

	private Class<? extends PositionEntity> entityClass;
	private Class<? extends LogicEntityService<? extends PositionEntity>> serviceClass;

	private <T extends PositionEntity> LogicServiceType(Class<T> entityClass,
			Class<? extends LogicEntityService<T>> logicServiceClass) {
		this.entityClass = entityClass;
		this.serviceClass = logicServiceClass;
	}

	@SuppressWarnings("unchecked")
	public static Class<LogicEntityService<PositionEntity>> getServiceClassByEntityClass(
			Class<? extends PositionEntity> entityClass) {
		for (LogicServiceType type : LogicServiceType.values()) {
			if (type.entityClass.equals(entityClass)) {
				return (Class<LogicEntityService<PositionEntity>>) type.serviceClass;
			}
		}
		throw new ConfigurationException("Unexpected entity class: "
				+ entityClass.getName());
	}

}
