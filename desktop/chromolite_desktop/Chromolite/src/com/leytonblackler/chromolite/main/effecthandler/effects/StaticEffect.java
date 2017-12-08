package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.effecthandler.Effect;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;
import javafx.scene.layout.Pane;

public class StaticEffect extends Effect {

    private static final int[][] SPECTRUM_COLOURS = {
            { 255, 0, 255 },
            { 255, 0, 0 },
            { 255, 165, 0 },
            { 255, 255, 0 },
            { 0, 255, 0 },
            { 0, 255, 255 },
            { 0, 0, 255 },
            { 128, 0, 128 }
    };

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
        int length = settings.getLEDStripLength();
        int totalSteps = Math.round((float) length / (float) SPECTRUM_COLOURS.length);
        for (int i = 0; i < SPECTRUM_COLOURS.length; i++) {
            int[] currentColour = SPECTRUM_COLOURS[i];
            for (int j = 0; j < totalSteps; j++) {
                int index = (i * totalSteps) + j;
                if (index >= length) {
                    return;
                }
                ledStripSimulation.setLED(index, currentColour[0], currentColour[1], currentColour[2]);
                int[] nextColor;
                if (i == SPECTRUM_COLOURS.length - 1) {
                    nextColor = SPECTRUM_COLOURS[0];
                    int lastSteps = length - ((SPECTRUM_COLOURS.length - 1) * totalSteps);
                    currentColour = nextColour(currentColour, nextColor, lastSteps, j);
                } else {
                    nextColor = SPECTRUM_COLOURS[i + 1];
                    currentColour = nextColour(currentColour, nextColor, totalSteps, j);
                }

            }
        }
    }

    private int[] nextColour(int[] currentColour, int[] nextColour, int totalSteps, int currentStep) {
        int[] colour = new int[3];
        int differenceR = nextColour[0] - currentColour[0];
        int differenceG = nextColour[1] - currentColour[1];
        int differenceB = nextColour[2] - currentColour[2];
        colour[0] = currentColour[0] + ((differenceR * currentStep) / totalSteps);
        colour[1] = currentColour[1] + ((differenceG * currentStep) / totalSteps);
        colour[2] = currentColour[2] + ((differenceB * currentStep) / totalSteps);
        //System.out.println(currentColour[0] + " " + currentColour[1] + " " + currentColour[2] + " --- " + colour[0] + " " + colour[1] + " " + colour[2]);
        return colour;
    }

}
