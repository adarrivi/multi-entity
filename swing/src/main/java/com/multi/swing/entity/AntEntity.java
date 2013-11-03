package com.multi.swing.entity;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import com.multi.swing.step.StepController;
import com.multi.swing.view.DrawUtils;
import com.multi.swing.view.GraphicsController;
import com.multi.swing.view.Images;

public class AntEntity implements Observer {

	private static final double ROTATION_DELTA = 0.1;
	private static final int FORWARD_STEP = 5;
	private static final Random RANDOM = new Random();

	private Point position;
	private double rotation = Math.PI * RANDOM.nextDouble();;
	private boolean rotateClockWise = RANDOM.nextBoolean();

	private Graphics2D graphics2d;
	private HormoneTraceEntity trace;

	public AntEntity(Point position, HormoneTraceEntity trace) {
		this.trace = trace;
		this.position = position;
	}

	@Override
	public void update(Observable observable, Object observableArgument) {
		if (observable instanceof GraphicsController) {
			graphics2d = (Graphics2D) observableArgument;
			drawAnt();
		}
		if (observable instanceof StepController) {
			rotateRandomly();
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
		trace.extendTrace(position);
		position.y -= Double.valueOf(Math.cos(rotation) * FORWARD_STEP)
				.intValue();
		position.x += Double.valueOf(Math.sin(rotation) * FORWARD_STEP)
				.intValue();
	}

	private void drawAnt() {
		AffineTransform oldTransform = graphics2d.getTransform();
		AffineTransform rotator = AffineTransform.getRotateInstance(rotation,
				position.x, position.y);
		graphics2d.setTransform(rotator);
		DrawUtils.getInstance().drawImage(Images.ANT, position, graphics2d);
		graphics2d.setTransform(oldTransform);
	}

}
