package com.multi.swing.service.view.impl;

import com.multi.swing.entity.AntEntity;
import com.multi.swing.entity.Entity;
import com.multi.swing.entity.HormoneTraceEntity;
import com.multi.swing.exception.ConfigurationException;
import com.multi.swing.service.view.ViewEntityService;

public enum ViewServiceType {

	ANT(AntEntity.class, AntViewSerivceDefaultImpl.class), //
	HORMONE_TRACE(HormoneTraceEntity.class,
			HormoneTraceViewSerivceDefaultImpl.class);

	private Class<? extends Entity> entityClass;
	private Class<? extends ViewEntityService<? extends Entity>> serviceClass;

	private <T extends Entity> ViewServiceType(Class<T> entityClass,
			Class<? extends ViewEntityService<T>> logicServiceClass) {
		this.entityClass = entityClass;
		this.serviceClass = logicServiceClass;
	}

	@SuppressWarnings("unchecked")
	public static Class<ViewEntityService<Entity>> getServiceClassByEntityClass(
			Class<? extends Entity> entityClass) {
		for (ViewServiceType type : ViewServiceType.values()) {
			if (type.entityClass.equals(entityClass)) {
				return (Class<ViewEntityService<Entity>>) type.serviceClass;
			}
		}
		throw new ConfigurationException("Unexpected entity class: "
				+ entityClass.getName());
	}

}
