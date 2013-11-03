package com.multi.swing.service.logic;

import com.multi.swing.entity.PositionEntity;

public interface LogicEntityService<T extends PositionEntity> {

	void stepEntity(T entity);
}
