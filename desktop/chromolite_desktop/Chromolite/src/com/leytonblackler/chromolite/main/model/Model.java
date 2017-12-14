package com.leytonblackler.chromolite.main.model;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.effecthandler.EffectHandler;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
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
    public void updateSpectrum(SettingsManager settings) {

    }

    @Override
    public void updateColours(SettingsManager settings) {

    }

    @Override
    public void updateModes(SettingsManager settings) {
        effectHandler.setEffect(settings.getMode());
    }

    @Override
    public void updateModeSettings(SettingsManager settings) {

    }

    @Override
    public void updatePlatformSettings(SettingsManager settings) {

    }

    @Override
    public void updateGeneralSettings(SettingsManager settings) {

    }

    @Override
    public void updateAndroidAppConnection(SettingsManager settings) {

    }
}
