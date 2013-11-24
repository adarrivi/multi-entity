package com.multi.framework.example;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import com.multi.framework.container.EntitiesContiner;
import com.multi.framework.controller.MainController;
import com.multi.framework.controller.StepCallback;
import com.multi.swing.config.SpringApplicationContext;

@Component
public class Main extends JFrame implements Runnable {
    private static final long serialVersionUID = 1L;

    @Value("${frame.height}")
    private int height;
    @Value("${frame.width}")
    private int width;

    @Autowired
    private EntitiesContiner entitiesContiner;
    @Autowired
    private MainController mainController;
    @Autowired
    private Sandbox sandbox;

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        AbstractApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringApplicationContext.class);
        applicationContext.registerShutdownHook();
        Main bean = applicationContext.getBean(Main.class);
        SwingUtilities.invokeLater(bean);

    }

    protected Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(Main.class.getSimpleName());
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void run() {
        centerFrame();
        add(sandbox);
        mainController.setStepExecutedFn(new StepCallback() {

            @Override
            public void stepExecuted() {
                for (int i = 0; i < 10; i++) {
                    entitiesContiner.addEntity(new Dog());
                }
            }
        });
        mainController.start();
    }

    private void centerFrame() {
        setSize(width, height);
        // set location must be called after setSize to center the frame
        setLocationRelativeTo(null);
    }

}
