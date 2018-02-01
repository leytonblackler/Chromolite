package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.main.effecthandler.EffectUtilities;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;

import java.util.ArrayList;
import java.util.List;

import static com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect.NumberOfColours.SPECTRUM;

public class CycleEffect extends Effect {

    private static final int STEPS = 20;

    public CycleEffect(LightSettings lightSettings) {
        super(lightSettings);
    }

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

    private int currentStep = 0;

    private int nextColourIndex = 1;

    private int[] currentColour = { 255, 0, 255 };
    private int[] nextColour = { 255, 0, 0 };

    @Override
    public void tick(EffectPlatform ... effectPlatforms) {

        int[][] colours = determineColours();
        processCurrentColour(colours);

        for (int platform = 0; platform < effectPlatforms.length; platform++) {
            List<int[][]> layouts = new ArrayList();
            for (int i = 0; i < effectPlatforms[platform].getLengths().length; i++) {
                //Process the layout for the effect platform.
                int[][] layout = EffectUtilities.generateSolidLayout(effectPlatforms[platform].getLengths()[i], currentColour);
                layouts.add(layout);
            }
            effectPlatforms[platform].setLayouts(layouts);
        }

        //==============================================================================================================
        /*if (lightSettings.getCycleNumberOfColours() == SPECTRUM) {
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
        }*/

        //System.out.println("current: " + currentColour[0] + " " + currentColour[1] + " " + currentColour[2]);
        //razerChromaService.setSingleDevices(currentColour[0], currentColour[1], currentColour[2]);
        //ledStripSimulation.setAll(currentColour[0], currentColour[1], currentColour[2]);
        //==============================================================================================================

        //Calculate how long to wait before the next tick.
        int time = EffectUtilities.calculateDelay(10, 50, lightSettings.getSpeed());

        delay(time);
    }

    protected int[][] determineColours() {
        int[][] colours = new int[0][0];
        switch (lightSettings.getCycleNumberOfColours()) {
            case TWO:
                colours = new int[2][3];
                colours[0] = lightSettings.getPrimaryColour();
                colours[1] = lightSettings.getSecondaryColour();
                break;
            case THREE:
                colours = new int[3][3];
                colours[0] = lightSettings.getPrimaryColour();
                colours[1] = lightSettings.getSecondaryColour();
                colours[2] = lightSettings.getTertiaryColour();
                break;
            case SPECTRUM:
                colours = EffectUtilities.SPECTRUM_COLOURS;
                break;
            case RANDOM:
                colours = new int[0][0];
                colours[0] = lightSettings.getPrimaryColour();
                colours[1] = lightSettings.getSecondaryColour();
                colours[2] = lightSettings.getTertiaryColour();
                break;
        }
        return colours;
    }

    private void processCurrentColour(int[][] colours) {
        //If the steps for the current colour/transition has reached maximum, reset to 0 and change next colour.
        if (currentStep >= STEPS) {
            currentStep = 0;
            nextColourIndex++;
        }

        //If the current colour is the last in the defined colours, set the next colour to be the first colour.
        if (nextColourIndex == colours.length) {
            nextColourIndex = 0;
        }

        switch (lightSettings.getCycleTransition()) {
            case BLEND:
                //TODO
                break;
            case FADE:
                //TODO
                break;
            case INSTANT:
                currentColour = colours[nextColourIndex > 0 ? nextColourIndex - 1 : colours.length - 1];
                break;
        }
        //System.out.println("current: " + currentStep + " next: " + nextColourIndex);
        currentStep++;
    }

    /*
    private void next(int[] currentColour) {
        int differenceR = nextColour[0] - currentColour[0];
        int differenceG = nextColour[1] - currentColour[1];
        int differenceB = nextColour[2] - currentColour[2];
        currentColour[0] = currentColour[0] + ((differenceR * currentStep) / STEPS);
        currentColour[1] = currentColour[1] + ((differenceG * currentStep) / STEPS);
        currentColour[2] = currentColour[2] + ((differenceB * currentStep) / STEPS);
        currentStep++;
    }*/
}
