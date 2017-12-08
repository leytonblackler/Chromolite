package com.leytonblackler.chromolite.main.effecthandler;

import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.settings.Settings;

public class EffectUtilities {

    public static final int[][] SPECTRUM_COLOURS = {
            { 255, 0, 255 },
            { 255, 0, 0 },
            { 255, 165, 0 },
            { 255, 255, 0 },
            { 0, 255, 0 },
            { 0, 255, 255 },
            { 0, 0, 255 },
            { 128, 0, 128 }
    };

    public static int calculateDelay(int min, int max, int percent) {
        //Requires that the minimum delay is less than the maximum delay and the percentage is valid.
        if (min > max || percent < 0 || percent > 100) {
            throw new IllegalArgumentException();
        }
        return (int) (((((100.0 - percent) / 100.0) * (max - min))) + min);
    }

    public static int[][] generateGradient(int length, int[][] colours) {
        //The RGB values for the colours that make up the gradient.
        int[][] gradient = new int[length][3];
        //The number of increments for the transition between each defined colour.
        int steps = (int) Math.floor((float) length / (float) colours.length);
        //Iterate across each of the defined colours, excluding the last colour.
        for (int i = 0; i < colours.length - 1; i++) {
            int[] current = colours[i];
            //Iterate across each of the steps between colours.
            for (int step = 0; step < steps; step++) {
                gradient[i * steps + step] = current;
                current = nextGradientColour(current, colours[i + 1], steps, step);
            }
        }
        //----
        //Iterate across each of the steps between colours.
        int[] current = colours[colours.length - 1];
        int remainingSteps = length - (colours.length - 1) * steps;
        for (int step = 0; step < remainingSteps; step++) {
            gradient[(colours.length - 1) * steps + step] = current;
            current = nextGradientColour(current, colours[0], remainingSteps, step);
        }

        return gradient;
    }

    /*private void setSpectrum(Settings settings, LEDStripSimulationController ledStripSimulation) {
        int length = settings.getLEDStripLength();
        int totalSteps = Math.round((float) length / (float) SPECTRUM_COLOURS.length);
        System.out.println(totalSteps);
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
                    int lastSteps = length - ((SPECTRUM_COLOURS.length - 1) * totalSteps);
                    for (int k = 0; k < lastSteps; k++) {
                        nextColor = SPECTRUM_COLOURS[0];
                        currentColour = nextColour(currentColour, nextColor, lastSteps, j);
                    }
                    return;
                } else {
                    nextColor = SPECTRUM_COLOURS[i + 1];
                    currentColour = nextColour(currentColour, nextColor, totalSteps, j);
                }
            }
        }
    }*/

    public static int[] nextGradientColour(int[] currentColour, int[] nextColour, int steps, int currentStep) {
        int[] colour = new int[3];
        int differenceR = nextColour[0] - currentColour[0];
        int differenceG = nextColour[1] - currentColour[1];
        int differenceB = nextColour[2] - currentColour[2];
        colour[0] = currentColour[0] + ((differenceR * currentStep) / steps);
        colour[1] = currentColour[1] + ((differenceG * currentStep) / steps);
        colour[2] = currentColour[2] + ((differenceB * currentStep) / steps);
        return colour;
    }

}
