package com.leytonblackler.chromolite.main.effects;

import javafx.scene.paint.Color;

import static com.leytonblackler.chromolite.main.effects.CycleEffect.NumberOfColours.RAINBOW;

public class CycleEffect extends Effect {

    private static final int STEPS = 20;

    private static final int[][] RAINBOW_COLOURS = {
            { 255, 0, 255 },
            { 255, 0, 0 },
            { 255, 165, 0 },
            { 255, 255, 0 },
            { 0, 255, 0 },
            { 0, 255, 255 },
            { 0, 0, 255 },
            { 128, 0, 128 }
    };

    public enum NumberOfColours {
        TWO,
        THREE,
        RAINBOW
    }

    private int[][] colours;

    private int currentStep = 0;

    private int nextColourIndex = 1;

    private int[] currentColour = { 255, 0, 255 };
    private int[] nextColour = { 255, 0, 0 };

    @Override
    public void tick() {
        super.tick();

        if (settings.getCycleNumberOfColours() == RAINBOW) {
            colours = RAINBOW_COLOURS;
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

        delay(50);
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
