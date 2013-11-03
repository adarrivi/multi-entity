package com.multi.swing.service.view.impl;

import com.multi.swing.entity.PositionEntity;
import com.multi.swing.entity.FeromoneEntity;
import com.multi.swing.entity.FoodEntity;
import com.multi.swing.entity.NestEntity;
import com.multi.swing.entity.ant.AntEntity;
import com.multi.swing.exception.ConfigurationException;
import com.multi.swing.service.view.ViewEntityService;

public enum ViewServiceType {

	ANT(AntEntity.class, AntViewSerivceDefaultImpl.class), //
	NEST(NestEntity.class, NestViewSerivceDefaultImpl.class), //
	FOOD(FoodEntity.class, FoodViewSerivceDefaultImpl.class), //
	FEROMONE(FeromoneEntity.class, FeromoneViewSerivceDefaultImpl.class);

	private Class<? extends PositionEntity> entityClass;
	private Class<? extends ViewEntityService<? extends PositionEntity>> serviceClass;

	private <T extends PositionEntity> ViewServiceType(Class<T> entityClass,
			Class<? extends ViewEntityService<T>> logicServiceClass) {
		this.entityClass = entityClass;
		this.serviceClass = logicServiceClass;
	}

	@SuppressWarnings("unchecked")
	public static Class<ViewEntityService<PositionEntity>> getServiceClassByEntityClass(
			Class<? extends PositionEntity> entityClass) {
		for (ViewServiceType type : ViewServiceType.values()) {
			if (type.entityClass.equals(entityClass)) {
				return (Class<ViewEntityService<PositionEntity>>) type.serviceClass;
			}
		}
		throw new ConfigurationException("Unexpected entity class: "
				+ entityClass.getName());
	}

}
