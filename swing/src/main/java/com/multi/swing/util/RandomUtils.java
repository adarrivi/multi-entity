package com.multi.swing.util;

import java.awt.Point;
import java.util.Random;

public class RandomUtils {

	private static final Random RANDOM = new Random();

	private static final RandomUtils INSTANCE = new RandomUtils();

	private RandomUtils() {
		// To limit scope
	}

	public static RandomUtils getIntance() {
		return INSTANCE;
	}

	public Point getPoint(int limitX, int limitY) {
		return new Point(RANDOM.nextInt(limitY), RANDOM.nextInt(limitY));
	}

	public double getRandomRotation() {
		return getDouble(2 * Math.PI);
	}

	public double getDouble(double limit) {
		return RANDOM.nextDouble() * limit;
	}

	public boolean getBoolean() {
		return RANDOM.nextBoolean();
	}

}
