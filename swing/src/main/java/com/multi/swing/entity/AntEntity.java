package com.multi.swing.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;
import java.util.Random;

import javax.swing.ImageIcon;

import com.multi.swing.step.StepController;
import com.multi.swing.view.GraphicsController;

public class AntEntity implements Observer {

	private static final int MAX_TRACE_LENGTH = 100;
	private static final double ROTATION_DELTA = 0.1;
	private static final int FORWARD_STEP = 5;
	private static final String IMAGE_PATH = "/img/ant.png";
	private static final Random RANDOM = new Random();
	private static final ImageIcon IMAGE_ICON = new ImageIcon(
			AntEntity.class.getResource(IMAGE_PATH));
	private static final Image IMAGE = IMAGE_ICON.getImage();

	private Point position;
	private double rotation = Math.PI * RANDOM.nextDouble();;
	private boolean rotateClockWise = RANDOM.nextBoolean();

	private Graphics2D graphics2d;

	private Queue<Point> trace = new LinkedList<>();

	public AntEntity(Point position) {
		this.position = position;
	}

	@Override
	public void update(Observable observable, Object observableArgument) {
		if (observable instanceof GraphicsController) {
			drawImage((Graphics2D) observableArgument);
		}
		if (observable instanceof StepController) {
			rotateRandomly();
			addTrace();
			moveForward();
		}
	}

	private void rotateRandomly() {
		rotateClockWise = RANDOM.nextBoolean();
		if (rotateClockWise) {
			rotation -= ROTATION_DELTA;
		} else {
			rotation += ROTATION_DELTA;
		}
		rotation -= ROTATION_DELTA;
	}

	private void moveForward() {
		position.y -= Double.valueOf(Math.cos(rotation) * FORWARD_STEP)
				.intValue();
		position.x += Double.valueOf(Math.sin(rotation) * FORWARD_STEP)
				.intValue();
	}

	private void addTrace() {
		trace.add(new Point(position));
		if (trace.size() > MAX_TRACE_LENGTH) {
			trace.poll();
		}
	}

	private void drawImage(Graphics2D graphics) {
		graphics2d = graphics;
		drawTrace();
		drawAnt();
	}

	private void drawTrace() {
		graphics2d.setColor(Color.GREEN);
		Point previous = null;
		for (Point current : trace) {
			if (previous != null) {
				graphics2d.drawLine(previous.x, previous.y, current.x,
						current.y);
			}
			previous = current;
		}
	}

	private void drawAnt() {
		AffineTransform oldTransform = graphics2d.getTransform();
		Point center = new Point(position);
		int width = IMAGE.getWidth(null);
		int height = IMAGE.getHeight(null);
		center.x -= width / 2;
		center.y -= height / 2;
		AffineTransform rotator = AffineTransform.getRotateInstance(rotation,
				position.x, position.y);
		graphics2d.setTransform(rotator);
		graphics2d.drawImage(IMAGE, center.x, center.y, null);
		graphics2d.setTransform(oldTransform);
	}

}
