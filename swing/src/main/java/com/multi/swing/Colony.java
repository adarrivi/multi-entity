package com.multi.swing;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import com.multi.swing.config.SpringApplicationContext;
import com.multi.swing.controller.logic.StepController;
import com.multi.swing.controller.view.GraphicsController;
import com.multi.swing.entity.AntEntity;
import com.multi.swing.entity.Entity;
import com.multi.swing.entity.FeromoneTraceEntity;

@Component
public class Colony extends JFrame implements Runnable {
	private static final int ANT_ENTITIES = 10;
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(Colony.class);

	@Value("${frame.height}")
	private int height;
	@Value("${frame.width}")
	private int width;

	@Value("${terrain.height}")
	private int terrainHeight;
	@Value("${terrain.width}")
	private int terrainWidth;

	@Autowired
	private Terrain terrain;
	@Autowired
	private StepController stepController;
	@Autowired
	private GraphicsController graphicsController;

	private List<Entity> entities = new ArrayList<>();

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				SpringApplicationContext.class);
		applicationContext.registerShutdownHook();
		LOG.debug("Application context loaded");
		Colony colony = applicationContext.getBean(Colony.class);
		SwingUtilities.invokeLater(colony);
	}

	protected Colony() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(Colony.class.getSimpleName());
		setResizable(false);
		setVisible(true);
	}

	@Override
	public void run() {
		centerFrame();
		add(terrain);

		Random random = new Random();
		for (int i = 0; i < ANT_ENTITIES; i++) {
			FeromoneTraceEntity trace = new FeromoneTraceEntity();
			AntEntity ant = new AntEntity(
					new Point(random.nextInt(terrainWidth),
							random.nextInt(terrainHeight)), trace);
			entities.add(trace);
			entities.add(ant);
		}

		graphicsController.addAllEntities(entities);
		stepController.addAllEntities(entities);

		LOG.debug("Main thread {}", Thread.currentThread());
		stepController.start();
	}

	private void centerFrame() {
		setSize(width, height);
		// set location must be called after setSize to center the frame
		setLocationRelativeTo(null);
	}
}
