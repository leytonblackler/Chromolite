package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.effecthandler.Effect;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

public class StaticEffect extends Effect {

    @Override
    public void tick(Settings settings, ArduinoController arduinoController, RazerChromaService razerChromaService, LEDStripSimulationController ledStripSimulation) {
        int[] colour = settings.getPrimaryColour();
        razerChromaService.setAll(colour[0], colour[1], colour[2]);
        ledStripSimulation.setAll(colour[0], colour[1], colour[2]);
        delay(100);
    }

}
