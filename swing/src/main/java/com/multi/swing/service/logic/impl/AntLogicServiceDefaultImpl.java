package com.multi.swing.service.logic.impl;

import org.springframework.stereotype.Service;

import com.multi.swing.entity.ant.AntEntity;
import com.multi.swing.entity.ant.AntState;
import com.multi.swing.service.logic.LogicEntityService;

@Service
class AntLogicServiceDefaultImpl implements LogicEntityService<AntEntity> {

	@Override
	public void stepEntity(AntEntity ant) {
		AntState currentState = ant.getState();
		currentState.doAction(ant);
		AntState nextState = currentState.getNextState(ant);
		ant.setState(nextState);
	}

}
