package com.multi.swing.controller.view;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Images {

	public static final Image ANT = new ImageIcon(
			Images.class.getResource("/img/ant.png")).getImage();

	public static final Image GREEN_HORMONE = new ImageIcon(
			Images.class.getResource("/img/greenHormone2.png")).getImage();

	private static Images INSTANCE = new Images();

	private Images() {
		// to limit scope
	}

	public static Images getInstance() {
		return INSTANCE;
	}

}
