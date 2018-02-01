package com.leytonblackler.chromolite.main.effecthandler;

import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.effecthandler.effects.*;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;

public class EffectThread {

    private volatile boolean running = false;

    private Effect effect;

    private LightSettings.Mode current;

    private EffectPlatform[] effectPlatforms;

    public EffectThread(EffectPlatform ... effectPlatforms) {
        this.effectPlatforms = effectPlatforms;
    }

    public void start() {
        Thread ticker = new Thread(() -> {
            running = true;
            while (running) {
                //If the effect has not yet been set, don't tick.
                if (effect == null) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    effect.tick(effectPlatforms);
                }
            }
        });
        ticker.start();
    }

    public void stop() {
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public LightSettings.Mode getCurrentMode() {
        return current;
    }

    public void setEffect(LightSettings lightSettings) {
        current = lightSettings.getMode();
        switch (current) {
            case STATIC:
                effect = new StaticEffect(lightSettings);
                break;
            case CYCLE:
                effect = new CycleEffect(lightSettings);
                break;
            case WAVE:
                effect = new WaveEffect(lightSettings);
                break;
            case MUSIC:
                //effectThread.setEffect(new MusicEffect(lightSettings));
                break;
            case SCAN:
                //effectThread.setEffect(new ScanEffect(lightSettings));
                break;
            case STROBE:
                //effectThread.setEffect(new StrobeEffect(lightSettings));
                break;
            case OFF:
                //effectThread.setEffect(new OffEffect(lightSettings));
                break;
            case DISABLE:
                //effectThread.setEffect(new DisableEffect(lightSettings, arduinoController, razerChromaService, ledStripSimulation));???
                break;
        }
    }
}
