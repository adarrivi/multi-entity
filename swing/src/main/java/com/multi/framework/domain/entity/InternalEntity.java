package com.multi.framework.domain.entity;

import java.util.ArrayList;
import java.util.Collection;

import com.multi.framework.domain.event.Event;

public class InternalEntity {

	private Entity entity;
	private Collection<Event> listeningEvents = new ArrayList<>();
	private Collection<Entity> visibleEntities = new ArrayList<>();
	private State currentState;

	public InternalEntity(Entity entity) {
		this.entity = entity;
	}

	public Collection<Event> getListeningEvents() {
		return listeningEvents;
	}

	public Collection<Entity> getVisibleEntities() {
		return visibleEntities;
	}

	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	public void clearEntity() {
		listeningEvents.clear();
		visibleEntities.clear();
	}

	public void addListeningEvent(Event event) {
		listeningEvents.add(event);
	}

	public void addVisibleEntity(Entity entity) {
		visibleEntities.add(entity);
	}

	public Entity getEntity() {
		return entity;
	}

}
