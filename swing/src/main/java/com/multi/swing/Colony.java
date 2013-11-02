package com.multi.swing;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import com.multi.swing.config.SpringApplicationContext;
import com.multi.swing.entity.Terrain;
import com.multi.swing.entity.AntEntity;
import com.multi.swing.step.StepController;
import com.multi.swing.view.GraphicsController;

@Component
public class Colony extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(Colony.class);

	@Value("${frame.height}")
	private int height;
	@Value("${frame.width}")
	private int width;

	@Autowired
	private Terrain terrain;
	@Autowired
	private StepController stepController;
	@Autowired
	private GraphicsController graphicsController;

	private List<AntEntity> entities = new ArrayList<>();

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				SpringApplicationContext.class);
		applicationContext.registerShutdownHook();
		LOG.debug("Application context loaded");
	}

	protected Colony() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(Colony.class.getSimpleName());
		setResizable(false);
		setVisible(true);
	}

	@PostConstruct
	private void initialize() {
		centerFrame();
		add(terrain);

		Random random = new Random();
		for (int i = 0; i < 50; i++) {
			entities.add(new AntEntity(new Point(random.nextInt(width),
					random.nextInt(height))));
		}
		graphicsController.addAllObservers(entities);
		stepController.addAllObservers(entities);
		stepController.addObserver(terrain);
		stepController.start();
	}

	private void centerFrame() {
		setSize(width, height);
		// set location must be called after setSize to center the frame
		setLocationRelativeTo(null);
	}
}
