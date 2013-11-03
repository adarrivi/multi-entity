package com.multi.swing.view;

import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

public class Images {

	public static final Image ANT = new ImageIcon(
			Images.class.getResource("/img/ant.png")).getImage();
	private static final Image GREEN_HORMONE = new ImageIcon(
			Images.class.getResource("/img/greenHormone.png")).getImage();

	private static final Image GREEN_HORMONE2 = new ImageIcon(
			Images.class.getResource("/img/greenHormone2.png")).getImage();

	private static final Image[] GREEN_HORMONE_ARRAY = { GREEN_HORMONE,
			GREEN_HORMONE2 };

	private static Random RANDOM = new Random();
	private static Images INSTANCE = new Images();

	private Images() {
		// to limit scope
	}

	public static Images getInstance() {
		return INSTANCE;
	}

	public Image getRandomGreenHormone() {
		int length = GREEN_HORMONE_ARRAY.length;
		return GREEN_HORMONE_ARRAY[RANDOM.nextInt(length)];
	}
}
