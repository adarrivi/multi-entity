package com.multi.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.multi.swing.controller.logic.StepController;
import com.multi.swing.controller.view.GraphicsController;

@Component
public class Terrain extends JPanel {
	private static final Logger LOG = LoggerFactory.getLogger(Terrain.class);
	private static final long serialVersionUID = 1L;

	@Value("${terrain.height}")
	private int height;
	@Value("${terrain.width}")
	private int width;

	@Autowired
	private GraphicsController graphicsController;

	@Autowired
	private StepController stepController;

	@PostConstruct
	private void initialize() {
		setBackground(Color.GRAY);
		setDoubleBuffered(true);
		setSize(width, height);
	}

	@Override
	public void paint(Graphics graphics) {
		long nanoTime = System.nanoTime();
		super.paint(graphics);
		graphicsController.propagateDrawingAction((Graphics2D) graphics);
		Toolkit.getDefaultToolkit().sync();
		graphics.dispose();
		LOG.debug("Took {}ms. to process drawing",
				TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - nanoTime));
		stepController.start();
	}

}
