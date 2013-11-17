package com.multi.framework.controller;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.InternalEntity;
import com.multi.framework.service.EntityAttributeServiceType;

public class EntitiesContinerTest {

	private EntitiesContiner victim = new EntitiesContiner();

	private Collection<InternalEntity> internalEntities;
	private EntityAttributeServiceType type;
	private Entity entity;

	@Test
	public void getInternalEntitiesByAttributeService_Empty_ReturnEmpty() {
		givenType(EntityAttributeServiceType.LISTEN);
		givenEmptyContainer();
		whengetInternalEntitiesByAttributeService();
		thenInternalEntitiesCollectionShouldBeEmpty();
	}

	private void givenType(EntityAttributeServiceType type) {
		this.type = type;
	}

	private void givenEmptyContainer() {
		// Nothing to do
	}

	private void whengetInternalEntitiesByAttributeService() {
		internalEntities = victim.getInternalEntitiesByAttributeService(type
				.getServiceInterfaceClass());
	}

	private void thenInternalEntitiesCollectionShouldBeEmpty() {
		Assert.assertNotNull(internalEntities);
		Assert.assertTrue(internalEntities.isEmpty());
	}

	@Test
	public void getInternalEntitiesByAttributeService_ContainsOtherType_ReturnEmpty() {
		givenType(EntityAttributeServiceType.LISTEN);
		givenMoveEntities();
		whengetInternalEntitiesByAttributeService();
		thenInternalEntitiesCollectionShouldBeEmpty();
	}

	private void givenMoveEntities() {
		entity = new MoveEntityStub();
		victim.addEntity(entity);
	}

	@Test
	public void getInternalEntitiesByAttributeService_OneEntitySameType_ReturnEntity() {
		givenType(EntityAttributeServiceType.MOVE);
		givenMoveEntities();
		whengetInternalEntitiesByAttributeService();
		thenInternalEntityShouldContainEntity();
	}

	private void thenInternalEntityShouldContainEntity() {
		Assert.assertEquals(1, internalEntities.size());
		Assert.assertEquals(entity, internalEntities.iterator().next()
				.getEntity());
	}

}
