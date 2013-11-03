package com.multi.swing;

import java.util.List;

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
import com.multi.swing.controller.logic.EntitiesController;
import com.multi.swing.controller.logic.StepController;
import com.multi.swing.controller.view.GraphicsController;
import com.multi.swing.entity.PositionEntity;
import com.multi.swing.entity.EntityFactory;

@Component
public class Colony extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(Colony.class);

	@Value("${frame.height}")
	private int height;
	@Value("${frame.width}")
	private int width;

	@Autowired
	private EntityFactory entityFactory;

	@Autowired
	private Terrain terrain;
	@Autowired
	private StepController stepController;
	@Autowired
	private GraphicsController graphicsController;
	@Autowired
	private EntitiesController entitiesController;

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
		List<PositionEntity> entities = entityFactory.createAntColony();
		entitiesController.addAllEntities(entities);
		LOG.debug("Main thread {}", Thread.currentThread());
		stepController.start();
	}

	private void centerFrame() {
		setSize(width, height);
		// set location must be called after setSize to center the frame
		setLocationRelativeTo(null);
	}
}
