package com.multi.swing.service.logic;

import com.multi.swing.entity.Entity;

public interface LogicEntityService<T extends Entity> {

	void stepEntity(T entity);
}
