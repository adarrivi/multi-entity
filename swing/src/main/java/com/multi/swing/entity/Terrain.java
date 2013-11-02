package com.multi.swing.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.multi.swing.view.GraphicsController;

@Component
public class Terrain extends JPanel implements ActionListener, Observer {

	private static final long serialVersionUID = 1L;

	@Value("${terrain.height}")
	private int height;
	@Value("${terrain.width}")
	private int width;

	@Autowired
	private GraphicsController graphicsController;

	@PostConstruct
	private void initialize() {
		setBackground(Color.GRAY);
		setDoubleBuffered(true);
		setSize(width, height);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		graphicsController.propagateDrawingAction((Graphics2D) graphics);
		Toolkit.getDefaultToolkit().sync();
		graphics.dispose();
	}

	@Override
	public void update(Observable observable, Object observableArg) {
		repaint();
	}
}
