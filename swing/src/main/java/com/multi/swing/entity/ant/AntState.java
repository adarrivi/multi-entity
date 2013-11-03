package com.multi.swing.entity.ant;

public interface AntState {

	void doAction(AntEntity ant);

	AntState getNextState(AntEntity ant);

}
