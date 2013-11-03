package com.multi.swing.service.view.impl;

import java.awt.Image;

import javax.annotation.PostConstruct;
import javax.swing.ImageIcon;

import org.springframework.stereotype.Component;

import com.multi.swing.entity.FeromonType;

@Component
class ImageCache {

	private final static int FOOD_SIZE = 9;

	public final Image ANT = new ImageIcon(getClass().getResource(
			"/img/ant.png")).getImage();

	public final Image GREEN_FEROMONE = new ImageIcon(getClass().getResource(
			"/img/greenFeromone20x20.png")).getImage();
	public final Image BROWN_FEROMONE = new ImageIcon(getClass().getResource(
			"/img/brownFeromone20x20.png")).getImage();

	public final Image NEST = new ImageIcon(getClass().getResource(
			"/img/nest.png")).getImage();

	public Image[] FOOD_ARRAY;

	@PostConstruct
	void createImagesArray() {
		FOOD_ARRAY = new Image[FOOD_SIZE];
		for (int i = 0; i < FOOD_SIZE; i++) {
			FOOD_ARRAY[i] = new ImageIcon(getClass().getResource(
					"/img/food" + i + ".png")).getImage();
		}
	}

	public Image getFeromoneImage(FeromonType type) {
		if (FeromonType.EXPLORE.equals(type)) {
			return GREEN_FEROMONE;
		}
		return BROWN_FEROMONE;
	}
}
