package com.multi.framework.example;

import com.multi.framework.domain.entity.Entity;
import com.multi.framework.domain.entity.action.Move;
import com.multi.framework.domain.entity.action.Survive;

public class Dog implements Entity, Move, Survive {

    private int liveSpan = 1000;

    public void step() {
        liveSpan--;
    }

    public boolean isAlive() {
        return liveSpan > 0;
    }
}
