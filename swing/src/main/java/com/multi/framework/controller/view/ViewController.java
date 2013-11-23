package com.multi.framework.controller.view;

import java.util.Observer;

public interface ViewController {

	void draw();

	void addObserver(Observer o);
}
