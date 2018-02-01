package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.main.effecthandler.EffectUtilities;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect.NumberOfColours.RANDOM;
import static com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect.NumberOfColours.SPECTRUM;
import static com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect.Transition.INSTANT;

public class CycleEffect extends Effect {

    private static final int STEPS = 40;

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

    private int[][] colours;

    private int currentStep = 0;

    private int nextColourIndex = 1;

    private int[] currentColour = { 255, 0, 255 };
    private int[] nextColour = { 255, 0, 0 };

    private NumberOfColours currentNumberOfColours = null;

    @Override
    public void tick(EffectPlatform ... effectPlatforms) {
        if (currentNumberOfColours != lightSettings.getCycleNumberOfColours()) {
            initialise();
        }

        determineColours();

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
        int time = EffectUtilities.calculateDelay(5, 25, lightSettings.getSpeed());

        delay(time);
    }

    protected void determineColours() {
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
                colours = new int[][] { generateRandomColour(), generateRandomColour() };
        }
    }

    private void processCurrentColour(int[][] colours) {

        if (lightSettings.getCycleNumberOfColours() != RANDOM) {
            nextColour = colours[nextColourIndex];
        }

        //If the step count for the current colour/transition has reached maximum, reset to 0 and change the next colour.
        if (currentStep >= STEPS) {
            currentStep = 0;
            nextColourIndex++;

            if (lightSettings.getCycleNumberOfColours() == RANDOM) {
                nextColour = generateRandomColour();
                if (lightSettings.getCycleTransition() == INSTANT) {
                    currentColour = nextColour;
                }
            }
        }

        //If the current colour is the last in the defined colours, set the next colour to be the first colour.
        if (nextColourIndex >= colours.length) {
            nextColourIndex = 0;
        }

            switch (lightSettings.getCycleTransition()) {
                case BLEND:
                    //TODO
                    break;
                case FADE:
                    currentColour = next(currentColour);
                    break;
                case INSTANT:
                    if (lightSettings.getCycleNumberOfColours() != RANDOM) {
                        currentColour = colours[nextColourIndex > 0 ? nextColourIndex - 1 : colours.length - 1];
                    }
                    break;
            }
        currentStep++;
    }

    private int[] next(int[] currentColour) {
        int[] newCurrentColour = new int[currentColour.length];
        int differenceR = nextColour[0] - currentColour[0];
        int differenceG = nextColour[1] - currentColour[1];
        int differenceB = nextColour[2] - currentColour[2];
        newCurrentColour[0] = currentColour[0] + ((differenceR * currentStep) / STEPS);
        newCurrentColour[1] = currentColour[1] + ((differenceG * currentStep) / STEPS);
        newCurrentColour[2] = currentColour[2] + ((differenceB * currentStep) / STEPS);
        return newCurrentColour;
    }

    private void initialise() {
        currentNumberOfColours = lightSettings.getCycleNumberOfColours();
        determineColours();
        currentStep = 0;
        nextColourIndex = 1;
        currentColour = colours[0];
        nextColour = colours[1];
    }

    private int[] generateRandomColour() {
        float hue = new Random().nextFloat();
        float saturation = 0.8f;//1.0 for brilliant, 0.0 for dull
        float luminance = 1.0f; //1.0 for brighter, 0.0 for black
        Color random = Color.getHSBColor(hue, saturation, luminance);
        return new int[] { random.getRed(), random.getGreen(), random.getBlue() };
    }
}
