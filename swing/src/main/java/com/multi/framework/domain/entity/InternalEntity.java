package com.multi.framework.domain.entity;

import java.util.ArrayList;
import java.util.Collection;

import com.multi.framework.domain.entity.action.Position;
import com.multi.framework.domain.entity.action.Shape;
import com.multi.framework.domain.entity.action.State;
import com.multi.framework.domain.event.Event;

public class InternalEntity {

	private Entity entity;
	private Collection<Event> listeningEvents = new ArrayList<>();
	private Collection<Entity> visibleEntities = new ArrayList<>();
	private State currentState;
	private Position currentPosition;
	private Shape currentShape;

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

	public void addListeningEvent(Event event) {
		listeningEvents.add(event);
	}

	public void clearListeningEvents() {
		listeningEvents.clear();
	}

	public void clearVisibleEntities() {
		visibleEntities.clear();
	}

	public void addVisibleEntity(Entity entity) {
		visibleEntities.add(entity);
	}

	public Entity getEntity() {
		return entity;
	}

	@SuppressWarnings("unchecked")
	public <T extends Position> T getCurrentPosition() {
		return (T) currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
	}

	public Shape getCurrentShape() {
		return currentShape;
	}

	public void setCurrentShape(Shape currentShape) {
		this.currentShape = currentShape;
	}

}
