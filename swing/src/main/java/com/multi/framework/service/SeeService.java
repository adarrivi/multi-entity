package com.multi.framework.service;

import java.util.Collection;

import com.multi.framework.domain.entity.Entity;

public interface SeeService<E extends Entity> extends AttributeService<E> {

	void seeEntities(E entity, Collection<E> entities);

}
