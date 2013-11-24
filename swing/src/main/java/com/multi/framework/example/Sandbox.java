package com.multi.framework.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Collection;
import java.util.Observer;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.multi.framework.container.EntitiesContiner;
import com.multi.framework.controller.logic.LogicController;
import com.multi.framework.controller.view.ViewController;
import com.multi.framework.domain.entity.InternalEntity;
import com.multi.swing.service.view.impl.ImageCache;
import com.multi.swing.util.DrawUtils;

@Component
public class Sandbox extends JPanel implements ViewController {
    private static final long serialVersionUID = 1L;

    @Value("${terrain.height}")
    private int height;
    @Value("${terrain.width}")
    private int width;

    @Autowired
    private ImageCache imageCache;
    @Autowired
    private EntitiesContiner entitiesContiner;
    @Autowired
    private LogicController logicController;

    private Observer observer;

    @PostConstruct
    private void initialize() {
        setBackground(Color.GRAY);
        setDoubleBuffered(true);
        setSize(width, height);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        drawDisplay(graphics);
        for (InternalEntity internalEntity : entitiesContiner.getAllEntities()) {
            if (internalEntity.getCurrentPosition() != null) {
                Position2d currentPosition = internalEntity.getCurrentPosition();
                Point position = new Point((int) currentPosition.getX(), (int) currentPosition.getY());
                // DrawUtils.getInstance().drawImage(imageCache.getAntStateImage(SearchFoodState.class,
                // 0), position, (Graphics2D) graphics);
                DrawUtils.getInstance().drawDot(position, (Graphics2D) graphics);
            }
        }

        Toolkit.getDefaultToolkit().sync();
        graphics.dispose();
        observer.update(null, null);
    }

    private void drawDisplay(Graphics graphics) {
        Collection<InternalEntity> allEntities = entitiesContiner.getAllEntities();
        graphics.setColor(Color.BLACK);
        graphics.drawString(Integer.toString(allEntities.size()), 10, 10);
        graphics.setColor(Color.BLACK);
    }

    @Override
    public void draw() {
        repaint();
    }

    @Override
    public void addObserver(Observer o) {
        observer = o;
    }
}
