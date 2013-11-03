package com.multi.swing.entity;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class HormoneTraceEntity implements Observer, Entity {

	private List<HormoneEntity> trace = new ArrayList<>();

	@Override
	public void update(Observable observable, Object observableArgument) {
	}

	public void extendTrace(Point position) {
		trace.add(new HormoneEntity(position));
	}

	public List<HormoneEntity> getHormones() {
		return trace;
	}
}
