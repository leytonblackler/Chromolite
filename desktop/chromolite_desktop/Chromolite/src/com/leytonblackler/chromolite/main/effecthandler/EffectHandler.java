package com.leytonblackler.chromolite.main.effecthandler;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.effecthandler.effects.*;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

import java.util.Timer;
import java.util.TimerTask;

public class EffectHandler {

    private static final int DELAY = 1;

    private Effect effect;

    private Timer timer;

    private SettingsManager settings;

    private ArduinoController arduinoController;

    private RazerChromaService razerChromaService;

    private LEDStripSimulationController ledStripSimulation;

    private volatile boolean running = false;

    public EffectHandler(SettingsManager settings, ArduinoController arduinoController, RazerChromaService razerChromaService, LEDStripSimulationController ledStripSimulation) {
        this.settings = settings;
        this.arduinoController = arduinoController;
        this.razerChromaService = razerChromaService;
        this.ledStripSimulation = ledStripSimulation;
    }

    public void run() {
        if (effect == null) {
            throw new IllegalStateException();
        }

        Thread ticker = new Thread(() -> {
            running = true;
            while (running) {
                effect.tick();
            }
        });

        ticker.start();
    }

    public void stop() {
        running = false;
        //Stop running the Razer Chroma SDK Service.
        razerChromaService.stop();
    }

    public void setEffect(LightSettings.Mode mode) {
        switch (mode) {
            case STATIC:
                effect = new StaticEffect(settings, arduinoController, razerChromaService, ledStripSimulation);
                break;
            case CYCLE:
                effect = new CycleEffect(settings, arduinoController, razerChromaService, ledStripSimulation);
                break;
            case WAVE:
                effect = new WaveEffect(settings, arduinoController, razerChromaService, ledStripSimulation);
                break;
            case MUSIC:
                effect = new MusicEffect(settings, arduinoController, razerChromaService, ledStripSimulation);
                break;
            case SCAN:
                effect = new ScanEffect(settings, arduinoController, razerChromaService, ledStripSimulation);
                break;
            case STROBE:
                effect = new StrobeEffect(settings, arduinoController, razerChromaService, ledStripSimulation);
                break;
            case OFF:
                effect = new OffEffect(settings, arduinoController, razerChromaService, ledStripSimulation);
                break;
            case DISABLE:
                //effect = new DisableEffect(settings, arduinoController, razerChromaService, ledStripSimulation);???
                break;
        }
    }
}
