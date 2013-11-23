package com.multi.framework.container;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multi.framework.domain.context.Context;
import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.InternalEntity;
import com.multi.framework.service.AttributeService;
import com.multi.framework.util.BeanLocator;

@Component
public class EntitiesContiner {

	private Collection<InternalEntity> allEntities = new ArrayList<>();
	private Map<Class<? extends Entity>, Map<AttributeType, InternalEntitiesServiceWrapper>> entityClassMap = new HashMap<>();
	private Context<?, ?> context;

	@Autowired
	private BeanLocator beanLocator;

	EntitiesContiner() {
		// Used by Spring Limiting scope
	}

	@SuppressWarnings("unchecked")
	public void addEntity(Entity entity) {
		Map<AttributeType, InternalEntitiesServiceWrapper> configurationMap = entityClassMap
				.get(entity.getClass());
		if (configurationMap == null) {
			configurationMap = new HashMap<>();
		}

		for (AttributeType type : AttributeType.values()) {
			if (type.getAttributeInterfaceClass().isAssignableFrom(
					entity.getClass())) {
				InternalEntitiesServiceWrapper entityAttributeConfiguration = configurationMap
						.get(type);
				if (entityAttributeConfiguration == null) {
					AttributeService<Entity> service = beanLocator
							.getServiceImplFromInterface(type
									.getServiceInterfaceClass());
					entityAttributeConfiguration = new InternalEntitiesServiceWrapper(
							service);
				}
				InternalEntity internalEntity = new InternalEntity(entity);
				allEntities.add(internalEntity);
				entityAttributeConfiguration.addInternalEntity(internalEntity);
				configurationMap.put(type, entityAttributeConfiguration);
			}
		}
		entityClassMap.put(entity.getClass(), configurationMap);
	}

	public List<InternalEntitiesServiceWrapper> getInternalEntitiesByAttributeService(
			AttributeType type) {
		List<InternalEntitiesServiceWrapper> services = new ArrayList<>();
		for (Map<AttributeType, InternalEntitiesServiceWrapper> entityWrapperByTypeMap : entityClassMap
				.values()) {
			InternalEntitiesServiceWrapper internalEntitiesServiceWrapper = entityWrapperByTypeMap
					.get(type);
			if (internalEntitiesServiceWrapper != null) {
				services.add(internalEntitiesServiceWrapper);
			}
		}
		return services;
	}

	public Context<?, ?> getContext() {
		return context;
	}

	public Collection<InternalEntity> getAllEntities() {
		return allEntities;
	}
}
