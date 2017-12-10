package com.leytonblackler.chromolite.main.model;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.effecthandler.EffectHandler;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.settings.SettingsObserver;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

public class Model extends SettingsObserver {

    /**
     * Handles the current effect.
     */
    private EffectHandler effectHandler;

    public Model(LEDStripSimulationController ledStripSimulation) {
        Chromolite.getInstance().getSettings().addObserver(this);

        effectHandler = new EffectHandler(new ArduinoController(), new RazerChromaService(), ledStripSimulation);
        effectHandler.setEffect(Chromolite.getInstance().getSettings().getMode());
        effectHandler.run();
    }

    public void stop() {
        //Stop running the effect handler.
        effectHandler.stop();
    }

    @Override
    public void updateSpectrum(Settings settings) {

    }

    @Override
    public void updateColours(Settings settings) {

    }

    @Override
    public void updateModes(Settings settings) {
        effectHandler.setEffect(settings.getMode());
    }

    @Override
    public void updateModeSettings(Settings settings) {

    }

    @Override
    public void updateGeneralSettings(Settings settings) {

    }

    @Override
    public void updateAndroidAppConnection(Settings settings) {

    }
}
