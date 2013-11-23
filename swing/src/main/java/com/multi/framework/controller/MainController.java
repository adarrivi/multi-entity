package com.multi.framework.controller;

import java.util.Observable;
import java.util.Observer;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.multi.framework.controller.logic.LogicController;
import com.multi.framework.controller.view.ViewController;

@Component
public class MainController implements Observer {

	@Autowired
	private LogicController logicController;
	@Autowired
	private ViewController viewController;

	@PostConstruct
	private void init() {
		viewController.addObserver(this);
	}

	public void start() {
		logicController.step();
		viewController.draw();
	}

	@Override
	public void update(Observable o, Object arg) {
		logicController.step();
		viewController.draw();
	}
}
