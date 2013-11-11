package com.multi.swing.service.view.impl;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.swing.ImageIcon;

import org.springframework.stereotype.Component;

import com.multi.swing.entity.PheromonType;
import com.multi.swing.entity.ant.AntState;
import com.multi.swing.entity.ant.FoodFoundState;
import com.multi.swing.entity.ant.SearchFoodState;

@Component
class ImageCache {

	private final static int FOOD_SIZE = 9;
	private final static int ANT_SEARCH_FOOD_SIZE = 2;
	private final static int ANT_FOOD_FOUND_SIZE = 2;

	public final Image GREEN_PHEROMONE = new ImageIcon(getClass().getResource(
			"/img/greenPheromone20x20.png")).getImage();
	public final Image BROWN_PHEROMONE = new ImageIcon(getClass().getResource(
			"/img/brownPheromone20x20.png")).getImage();

	public final Image NEST = new ImageIcon(getClass().getResource(
			"/img/nest.png")).getImage();

	public Image[] FOOD_ARRAY;
	private Map<Class<? extends AntState>, Image[]> ANT_STATE_MAP;

	@PostConstruct
	private void createImagesArray() {
		FOOD_ARRAY = new Image[FOOD_SIZE];
		for (int i = 0; i < FOOD_SIZE; i++) {
			FOOD_ARRAY[i] = getPngImage("/img/food", i);
		}
		ANT_STATE_MAP = new HashMap<Class<? extends AntState>, Image[]>();
		Image[] antSeach = new Image[ANT_SEARCH_FOOD_SIZE];
		for (int i = 0; i < ANT_SEARCH_FOOD_SIZE; i++) {
			antSeach[i] = getPngImage("/img/antSearch", i);
		}
		ANT_STATE_MAP.put(SearchFoodState.class, antSeach);

		antSeach = new Image[ANT_FOOD_FOUND_SIZE];
		for (int i = 0; i < ANT_FOOD_FOUND_SIZE; i++) {
			antSeach[i] = getPngImage("/img/antFoodFound", i);
		}
		ANT_STATE_MAP.put(FoodFoundState.class, antSeach);

	}

	private Image getPngImage(String name, int index) {
		return new ImageIcon(getClass().getResource(name + index + ".png"))
				.getImage();
	}

	public Image getPheromoneImage(PheromonType type) {
		if (PheromonType.EXPLORE.equals(type)) {
			return GREEN_PHEROMONE;
		}
		return BROWN_PHEROMONE;
	}

	public Image getAntStateImage(Class<? extends AntState> antStateClass,
			int index) {
		return ANT_STATE_MAP.get(antStateClass)[index];

	}
}
