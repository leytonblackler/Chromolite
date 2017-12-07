package com.leytonblackler.chromolite.main.effecthandler;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.effecthandler.effects.*;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

import java.util.Timer;
import java.util.TimerTask;

public class EffectHandler {

    private static final int DELAY = 200;

    private Effect effect;

    private Timer timer;

    private ArduinoController arduinoController;

    private RazerChromaService razerChromaService;

    LEDStripSimulationController ledStripSimulation;

    public EffectHandler(ArduinoController arduinoController, RazerChromaService razerChromaService, LEDStripSimulationController ledStripSimulation) {
        this.arduinoController = arduinoController;
        this.razerChromaService = razerChromaService;
        this.ledStripSimulation = ledStripSimulation;
    }

    public void run() {
        if (effect == null) {
            throw new IllegalStateException();
        }

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                effect.tick(Chromolite.getInstance().getSettings(), arduinoController, razerChromaService, ledStripSimulation);
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
            case STATIC:
                effect = new StaticEffect();
                break;
            case RANDOM:
                effect = new RandomEffect();
                break;
            case WAVE:
                effect = new WaveEffect();
                break;
            case MUSIC:
                effect = new MusicEffect();
                break;
            case CYCLE:
                effect = new CycleEffect();
                break;
            case STROBE:
                effect = new StrobeEffect();
                break;
            case SCAN:
                effect = new ScanEffect();
                break;
            case OFF:
                effect = new OffEffect();
                break;
        }
    }
}
