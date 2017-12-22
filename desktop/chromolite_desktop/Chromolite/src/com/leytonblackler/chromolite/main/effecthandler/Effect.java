package com.leytonblackler.chromolite.main.effecthandler;

import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.settings.categories.PlatformSettings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

public abstract class Effect {

    protected SettingsManager settings;

    private ArduinoController arduinoController;
    private RazerChromaService razerChromaService;
    private LEDStripSimulationController ledStripSimulation;

    public Effect(SettingsManager settings, ArduinoController arduinoController, RazerChromaService razerChromaService, LEDStripSimulationController ledStripSimulation) {
        this.settings = settings;
        this.arduinoController = arduinoController;
        this.razerChromaService = razerChromaService;
        this.ledStripSimulation = ledStripSimulation;
    }

    public abstract void tick();

    protected void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void setLEDSimulation(int[][] layout) {
        if (settings.getPlatform() != PlatformSettings.Platform.RAZER_CHROMA || settings.getSyncPlatforms()) {
            ledStripSimulation.setLayout(layout);
        }
    }

    protected void setRazerChroma(int[] singleDeviceColour, int[][] keyboardLayout, int[][] mouseLayout, int[][] mousepadLayout) {
        if (settings.getPlatform() != PlatformSettings.Platform.RAZER_CHROMA || settings.getSyncPlatforms()) {
            razerChromaService.setSingleDevices(singleDeviceColour[0], singleDeviceColour[1], singleDeviceColour[2]);
            razerChromaService.setKeyboardLayout(keyboardLayout);
            razerChromaService.setMouseLayout(mouseLayout);
            razerChromaService.setMousepadLayout(mousepadLayout);
        }
    }

    //protected abstract int[][] determineColours();

}
