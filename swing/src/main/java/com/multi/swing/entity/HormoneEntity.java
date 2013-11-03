package com.multi.swing.entity;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import com.multi.swing.view.DrawUtils;
import com.multi.swing.view.Images;

public class HormoneEntity {

	private static final float FAINT_DELTA = 0.01f;
	private Image image = Images.getInstance().getRandomGreenHormone();
	private float intensity = 1;
	private final Point position;

	public HormoneEntity(Point position) {
		this.position = new Point(position);
	}

	public void step() {
		intensity -= FAINT_DELTA;
	}

	public void draw(Graphics2D graphics2d) {
		Composite oldComposite = graphics2d.getComposite();
		int rule = AlphaComposite.SRC_OVER;
		Composite comp = AlphaComposite.getInstance(rule, intensity);
		graphics2d.setComposite(comp);
		DrawUtils.getInstance().drawImage(image, position, graphics2d);
		graphics2d.setComposite(oldComposite);
	}

	public boolean hasDissapeared() {
		return intensity <= 0;
	}

}
