package com.multi.framework.service;

import com.multi.framework.domain.entity.Entity;

public interface SurviveService<E extends Entity> extends AttributeService<E> {

	boolean survives(E entity);

}
