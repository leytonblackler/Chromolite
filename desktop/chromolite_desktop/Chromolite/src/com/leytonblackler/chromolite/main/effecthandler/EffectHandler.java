package com.leytonblackler.chromolite.main.effecthandler;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.main.effecthandler.effects.*;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

import java.util.Timer;
import java.util.TimerTask;

public class EffectHandler {

    private static final int DELAY = 10;

    private Effect effect;

    private Timer timer;

    private ArduinoController arduinoController;

    private RazerChromaService razerChromaService;

    public EffectHandler(ArduinoController arduinoController, RazerChromaService razerChromaService) {
        this.arduinoController = arduinoController;
        this.razerChromaService = razerChromaService;
    }

    public void run() {
        if (effect == null) {
            throw new IllegalStateException();
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                effect.tick(Chromolite.getInstance().getSettings(), arduinoController, razerChromaService);
            }
        }, DELAY, DELAY);

    }

    public void stop() {
        if (timer == null) {
            throw new IllegalStateException();
        }
        timer.cancel();

        //Stop running the Razer Chroma SDK Service.
        razerChromaService.stop();
    }

    public void setEffect(Settings.Mode mode) {
        switch (mode) {
            case STATIC: effect = new StaticEffect();
            case RANDOM: effect = new RandomEffect();
            case WAVE: effect = new WaveEffect();
            case MUSIC: effect = new MusicEffect();
            case CYCLE: effect = new CycleEffect();
            case STROBE: effect = new StrobeEffect();
            case SCAN: effect = new ScanEffect();
            case OFF: effect = new OffEffect();
        }
    }
}
