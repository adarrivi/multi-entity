package com.multi.swing.controller.view;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Images {

	public static final Image ANT = new ImageIcon(
			Images.class.getResource("/img/ant.png")).getImage();

	public static final Image GREEN_FEROMONE = new ImageIcon(
			Images.class.getResource("/img/greenFeromone20x20.png")).getImage();

	private static Images INSTANCE = new Images();

	private Images() {
		// to limit scope
	}

	public static Images getInstance() {
		return INSTANCE;
	}

}
