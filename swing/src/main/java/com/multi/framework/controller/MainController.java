package com.multi.framework.controller;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.multi.framework.controller.logic.LogicController;
import com.multi.framework.controller.view.ViewController;

@Component
public class MainController implements Observer {

    @Autowired
    private LogicController logicController;
    @Autowired
    private ViewController viewController;

    private StepCallback stepExecutedFn;

    private long elapsedTimeNano;
    @Value("${frame.minMillisPerFrame}")
    private int minMillisPerFrame;

    @Value("${frame.skipFrames}")
    private int skipFrames;
    private double steps;

    @PostConstruct
    private void init() {
        viewController.addObserver(this);
    }

    public void setStepExecutedFn(StepCallback stepExecutedFn) {
        this.stepExecutedFn = stepExecutedFn;
    }

    public void start() {
        executeStep();
    }

    private void executeStep() {
        steps++;
        long currentTime = System.nanoTime();
        logicController.step();

        if (drawFrame()) {
            viewController.draw();
        }
        if (stepExecutedFn != null) {
            stepExecutedFn.stepExecuted();
        }
        if (elapsedTimeNano != 0) {
            sleepIfTooFast();
        }
        elapsedTimeNano = currentTime;

        if (!drawFrame()) {
            update(null, null);
        }
    }

    private void sleepIfTooFast() {
        long currentTime = System.nanoTime();
        long nanosPerFrame = TimeUnit.MILLISECONDS.toNanos(minMillisPerFrame);
        long toSleepMs = TimeUnit.NANOSECONDS.toMillis(nanosPerFrame - (currentTime - elapsedTimeNano));
        if (toSleepMs > 0) {
            try {
                Thread.sleep(toSleepMs);
            } catch (InterruptedException e) {
                // Nothing to do
            }
        }
    }

    private boolean drawFrame() {
        return skipFrames == 0 || steps % skipFrames == 0;
    }

    @Override
    public void update(Observable o, Object arg) {
        executeStep();
    }
}
