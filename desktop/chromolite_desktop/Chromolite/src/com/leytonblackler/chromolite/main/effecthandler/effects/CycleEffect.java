package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.effecthandler.Effect;
import com.leytonblackler.chromolite.main.effecthandler.EffectUtilities;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

import static com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect.NumberOfColours.SPECTRUM;

public class CycleEffect extends Effect {

    private static final int STEPS = 20;

    public enum NumberOfColours {
        TWO,
        THREE,
        SPECTRUM,
        RANDOM
    }

    public enum Transition {
        BLEND,
        FADE,
        INSTANT
    }

    private int[][] colours;

    private int currentStep = 0;

    private int nextColourIndex = 1;

    private int[] currentColour = { 255, 0, 255 };
    private int[] nextColour = { 255, 0, 0 };

    @Override
    public void tick(Settings settings, ArduinoController arduinoController, RazerChromaService razerChromaService, LEDStripSimulationController ledStripSimulation) {
        if (settings.getCycleNumberOfColours() == SPECTRUM) {
            colours = EffectUtilities.SPECTRUM_COLOURS;
        }

        if (currentStep < STEPS) {
            next(currentColour);
        } else {
            currentStep = 0;
            if (nextColourIndex == colours.length) {
                nextColourIndex = 0;
            }
            nextColour = colours[nextColourIndex];
            System.out.println("next[" + nextColourIndex + "]: " + nextColour[0] + " " + nextColour[1] + " " + nextColour[2]);
            nextColourIndex++;
        }

        //System.out.println("current: " + currentColour[0] + " " + currentColour[1] + " " + currentColour[2]);
        razerChromaService.setAll(currentColour[0], currentColour[1], currentColour[2]);
        ledStripSimulation.setAll(currentColour[0], currentColour[1], currentColour[2]);

        //Calculate how long to wait before the next tick.
        int time = EffectUtilities.calculateDelay(10, 50, settings.getSpeed());

        delay(time);
    }

    private void next(int[] currentColour) {
        int differenceR = nextColour[0] - currentColour[0];
        int differenceG = nextColour[1] - currentColour[1];
        int differenceB = nextColour[2] - currentColour[2];
        currentColour[0] = currentColour[0] + ((differenceR * currentStep) / STEPS);
        currentColour[1] = currentColour[1] + ((differenceG * currentStep) / STEPS);
        currentColour[2] = currentColour[2] + ((differenceB * currentStep) / STEPS);
        currentStep++;
    }
}
