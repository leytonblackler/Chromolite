package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;

import java.util.List;

public abstract class Effect {

    protected LightSettings lightSettings;

    public Effect(LightSettings lightSettings) {
        this.lightSettings = lightSettings;
        //this.arduinoController = arduinoController;
        //this.razerChromaService = razerChromaService;
        //this.ledStripSimulation = ledStripSimulation;
    }

    public abstract void tick(EffectPlatform ... effectPlatforms);

    protected void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*protected void setLEDSimulation(int[][] layout) {
        if (settings.getPlatform() != PlatformSettings.Platform.RAZER_CHROMA || settings.getSyncPlatforms()) {
            ledStripSimulation.setLayout(layout);
        }
    }*/

    /*protected void setRazerChroma(int[] singleDeviceColour, int[][] keyboardLayout, int[][] mouseLayout, int[][] mousepadLayout) {
        if (settings.getPlatform() != PlatformSettings.Platform.RAZER_CHROMA || settings.getSyncPlatforms()) {
            razerChromaService.setSingleDevices(singleDeviceColour[0], singleDeviceColour[1], singleDeviceColour[2]);
            razerChromaService.setKeyboardLayout(keyboardLayout);
            razerChromaService.setMouseLayout(mouseLayout);
            razerChromaService.setMousepadLayout(mousepadLayout);
        }
    }*/

    //protected abstract int[][] determineColours();

}
