package com.multi.swing.service.logic.impl;

import com.multi.swing.entity.AntEntity;
import com.multi.swing.entity.Entity;
import com.multi.swing.entity.HormoneTraceEntity;
import com.multi.swing.exception.ConfigurationException;
import com.multi.swing.service.logic.LogicEntityService;

public enum LogicServiceType {

	ANT(AntEntity.class, AntLogicServiceDefaultImpl.class), //
	HORMONE_TRACE(HormoneTraceEntity.class,
			HormoneTraceLogicServiceDefaultImpl.class);

	private Class<? extends Entity> entityClass;
	private Class<? extends LogicEntityService<? extends Entity>> serviceClass;

	private <T extends Entity> LogicServiceType(Class<T> entityClass,
			Class<? extends LogicEntityService<T>> logicServiceClass) {
		this.entityClass = entityClass;
		this.serviceClass = logicServiceClass;
	}

	@SuppressWarnings("unchecked")
	public static Class<LogicEntityService<Entity>> getServiceClassByEntityClass(
			Class<? extends Entity> entityClass) {
		for (LogicServiceType type : LogicServiceType.values()) {
			if (type.entityClass.equals(entityClass)) {
				return (Class<LogicEntityService<Entity>>) type.serviceClass;
			}
		}
		throw new ConfigurationException("Unexpected entity class: "
				+ entityClass.getName());
	}

}
