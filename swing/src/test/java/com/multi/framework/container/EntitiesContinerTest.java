package com.multi.framework.container;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.InternalEntity;

public class EntitiesContinerTest {

	private EntitiesContiner victim = new EntitiesContiner();

	private Collection<InternalEntity> internalEntities;
	private AttributeType type;
	private Entity entity;

	@Test
	public void getInternalEntitiesByAttributeService_Empty_ReturnEmpty() {
		givenType(AttributeType.LISTEN);
		givenEmptyContainer();
		whengetInternalEntitiesByAttributeService();
		thenInternalEntitiesCollectionShouldBeEmpty();
	}

	private void givenType(AttributeType type) {
		this.type = type;
	}

	private void givenEmptyContainer() {
		// Nothing to do
	}

	private void whengetInternalEntitiesByAttributeService() {
		// internalEntities = victim.getInternalEntitiesByAttributeService(
		// entity.getClass(), type.getServiceInterfaceClass());
	}

	private void thenInternalEntitiesCollectionShouldBeEmpty() {
		Assert.assertNotNull(internalEntities);
		Assert.assertTrue(internalEntities.isEmpty());
	}

	@Test
	public void getInternalEntitiesByAttributeService_ContainsOtherType_ReturnEmpty() {
		givenType(AttributeType.LISTEN);
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
		givenType(AttributeType.MOVE);
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
