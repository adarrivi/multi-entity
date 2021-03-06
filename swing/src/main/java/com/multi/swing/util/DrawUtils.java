package com.multi.swing.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

public class DrawUtils {

	private static final DrawUtils INSTANCE = new DrawUtils();

	public static DrawUtils getInstance() {
		return INSTANCE;
	}

	private DrawUtils() {
		// to limit scope
	}

	public void drawImage(Image image, Point position, Graphics2D graphics2d) {
		int width = image.getWidth(null);
		int height = image.getHeight(null);
		Point center = new Point(position);
		center.x -= width / 2;
		center.y -= height / 2;
		graphics2d.drawImage(image, center.x, center.y, null);
	}

	public void drawDot(Point position, Graphics2D graphics2d) {
		graphics2d.drawLine(position.x, position.y, position.x + 1, position.y);
	}

}
