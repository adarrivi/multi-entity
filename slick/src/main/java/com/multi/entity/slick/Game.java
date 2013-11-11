package com.multi.entity.slick;

import java.net.URL;
import java.util.Random;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {

	/** Screen width */
	private static final int WIDTH = 800;
	/** Screen height */
	private static final int HEIGHT = 600;

	private static final int SIZE = 100000;
	private static final int TRACE_SIZE = 0;

	private static final Random RANDOM = new Random();
	private Vector2f positions[] = new Vector2f[SIZE];
	private Vector2f trace[][] = new Vector2f[SIZE][TRACE_SIZE];

	private Image ant;
	private Image pheromone;

	/** A counter... */
	private int counter;

	public Game() throws SlickException {
		super("A Slick2d game");
	}

	public void render(GameContainer container, Graphics g)
			throws SlickException {
		g.drawString("Hello, " + Integer.toString(counter) + "!", 50, 50);
		for (int i = 0; i < SIZE; i++) {
			ant.draw(positions[i].x, positions[i].y);
			for (int j = 0; j < TRACE_SIZE; j++) {
				pheromone.draw(trace[i][j].x, trace[i][j].y);
			}
		}

	}

	@Override
	public void init(GameContainer container) throws SlickException {
		counter = 0;
		URL resource = getClass().getResource("/ant.png");
		ant = new Image(resource.getPath());
		resource = getClass().getResource("/greenPheromone20x20.png");
		pheromone = new Image(resource.getPath());
		for (int i = 0; i < SIZE; i++) {
			positions[i] = new Vector2f(RANDOM.nextFloat() * WIDTH,
					RANDOM.nextFloat() * HEIGHT);
			for (int j = 0; j < TRACE_SIZE; j++) {
				float deltaFloat = j * 3;
				trace[i][j] = new Vector2f((positions[i].x - deltaFloat)
						% WIDTH, (positions[i].y - deltaFloat) % HEIGHT);
			}
		}
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		counter++;
		for (int i = 0; i < SIZE; i++) {
			positions[i].x = (positions[i].x + 1) % WIDTH;
			positions[i].y = (positions[i].y + 1) % HEIGHT;
			for (int j = 0; j < TRACE_SIZE; j++) {
				float deltaFloat = j * 3;
				trace[i][j] = new Vector2f((positions[i].x - deltaFloat)
						% WIDTH, (positions[i].y - deltaFloat) % HEIGHT);
			}
		}
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new Game());
		app.setDisplayMode(WIDTH, HEIGHT, false);
		app.setForceExit(false);
		app.start();
	}

}
