package com.leytonblackler.chromolite.main.effects;

import java.util.Timer;
import java.util.TimerTask;

public class EffectHandler {

    private static final int DELAY = 10;

    private Effect effect;

    private Timer timer;

    public void run() {
        if (effect == null) {
            throw new IllegalStateException();
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                effect.tick();
            }
        }, DELAY, DELAY);

    }

    public void stop() {
        if (timer == null) {
            throw new IllegalStateException();
        }

        timer.cancel();
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }
}
