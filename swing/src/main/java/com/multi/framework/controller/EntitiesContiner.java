package com.multi.framework.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.multi.framework.domain.context.Context;
import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.InternalEntity;
import com.multi.framework.service.AttributeService;
import com.multi.framework.service.EntityAttributeServiceType;

@Component
public class EntitiesContiner {

	private Context<?, ?> context;
	private Map<EntityAttributeServiceType, Collection<InternalEntity>> internalEntityMap = new HashMap<EntityAttributeServiceType, Collection<InternalEntity>>();

	EntitiesContiner() {
		// Used by Spring
	}

	public void addEntity(Entity entity) {
		addToMapByAttributeType(entity);
	}

	private void addToMapByAttributeType(Entity entity) {
		InternalEntity internalEntity = new InternalEntity(entity);
		for (EntityAttributeServiceType type : EntityAttributeServiceType
				.values()) {
			if (type.getAttributeInterfaceClass().isAssignableFrom(
					entity.getClass())) {
				Collection<InternalEntity> entities = internalEntityMap
						.get(type.getAttributeInterfaceClass());
				if (entities == null) {
					entities = new ArrayList<>();
				}
				entities.add(internalEntity);
				internalEntityMap.put(type, entities);
			}
		}
	}

	public Collection<InternalEntity> getInternalEntitiesByAttributeService(
			Class<? extends AttributeService> serviceClass) {
		EntityAttributeServiceType type = EntityAttributeServiceType
				.getTypeByServiceInterface(serviceClass);
		return getInternalEntitiesByAttribute(type);
	}

	private Collection<InternalEntity> getInternalEntitiesByAttribute(
			EntityAttributeServiceType type) {
		Collection<InternalEntity> collection = internalEntityMap.get(type);
		if (collection == null) {
			return Collections.emptyList();
		}
		return collection;
	}

	public Context<?, ?> getContext() {
		return context;
	}

}
