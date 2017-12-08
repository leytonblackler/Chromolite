package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.effecthandler.Effect;
import com.leytonblackler.chromolite.main.effecthandler.EffectUtilities;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;
import javafx.scene.layout.Pane;

public class StaticEffect extends Effect {

    public enum Style {
        SOLID,
        GRADIENT,
        ALTERNATING
    }

    public enum NumberOfColours {
        ONE,
        TWO,
        THREE,
        SPECTRUM
    }

    @Override
    public void tick(Settings settings, ArduinoController arduinoController, RazerChromaService razerChromaService, LEDStripSimulationController ledStripSimulation) {
        int[] primary = settings.getPrimaryColour();
        int[] secondary = settings.getSecondaryColour();
        int[] tertiary = settings.getTertiaryColour();
        razerChromaService.setAll(primary[0], primary[1], primary[2]);

        //setThree(settings, ledStripSimulation, primary, secondary, tertiary);
        setSpectrum(settings, ledStripSimulation);

        delay(100);
    }

    private void setThree(Settings settings, LEDStripSimulationController ledStripSimulation, int[] primary, int[] secondary, int[] tertiary) {
        for (int i = 0; i < settings.getLEDStripLength(); i++) {
            int[] colour;
            int length = settings.getLEDStripLength();
            if (i < length / 3) {
                colour = primary;
            } else if (i < 2 * (length / 3)) {
                colour = secondary;
            } else {
                colour = tertiary;
            }
            ledStripSimulation.setLED(i, colour[0], colour[1], colour[2]);
        }
    }

    private void setSpectrum(Settings settings, LEDStripSimulationController ledStripSimulation) {
        int[][] gradient = EffectUtilities.generateGradient(settings.getLEDStripLength(), EffectUtilities.SPECTRUM_COLOURS);
        for (int led = 0; led < settings.getLEDStripLength(); led++) {
            ledStripSimulation.setLED(led, gradient[led][0], gradient[led][1], gradient[led][2]);
        }
    }

}
