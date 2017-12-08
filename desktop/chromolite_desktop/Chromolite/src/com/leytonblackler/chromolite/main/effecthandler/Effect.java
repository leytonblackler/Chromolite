package com.leytonblackler.chromolite.main.effecthandler;

import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

public abstract class Effect {

    public abstract void tick(Settings settings, ArduinoController arduinoController, RazerChromaService razerChromaService, LEDStripSimulationController ledStripSimulation);

    protected void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
