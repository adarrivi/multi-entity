package com.multi.framework.container;

import java.util.ArrayList;
import java.util.List;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.InternalEntity;
import com.multi.framework.service.AttributeService;

public class InternalEntitiesServiceWrapper {

	private AttributeService<Entity> attributeService;
	private List<InternalEntity> internalEntities = new ArrayList<>();

	InternalEntitiesServiceWrapper(AttributeService<Entity> attributeService) {
		this.attributeService = attributeService;
	}

	void addInternalEntity(InternalEntity internalEntity) {
		internalEntities.add(internalEntity);
	}

	@SuppressWarnings("unchecked")
	public <T extends AttributeService<Entity>> T getAttributeService() {
		return (T) attributeService;
	}

	public List<InternalEntity> getInternalEntities() {
		return internalEntities;
	}

}
