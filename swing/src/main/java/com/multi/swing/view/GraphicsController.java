package com.multi.swing.view;

import java.awt.Graphics2D;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import org.springframework.stereotype.Component;

@Component
public class GraphicsController extends Observable {

	public void propagateDrawingAction(Graphics2D graphics2d) {
		setChanged();
		notifyObservers(graphics2d);
	}

	public void addAllObservers(Collection<? extends Observer> entities) {
		for (Observer observer : entities) {
			addObserver(observer);
		}
	}
}
