package com.leytonblackler.chromolite.main.effecthandler;

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

    public static int[][] generateGradientLayout(int length, int[][] colours) {
        //The RGB values for the colours that make up the gradient.
        int[][] gradient = new int[length][3];
        //The number of increments for the transition between each defined colour.
        int steps = (int) Math.floor((float) length / (float) colours.length);
        //Calculate the remaining number of steps.
        int remainingSteps = length - (colours.length) * steps;
        //Fill in the layout.
        int position = 0;
        for (int colour = 0; colour < colours.length; colour++) {
            //Calculate how many steps there are for the current transition.
            int sectionSteps = steps;
            if (remainingSteps > 0) {
                sectionSteps++;
                remainingSteps--;
            }
            //Fill in the transition for colours, excluding the last colour.
            if (colour < colours.length - 1) {
                int[] current = colours[colour];
                //Iterate across each of the steps between colours.
                for (int step = 0; step < sectionSteps; step++) {
                    gradient[position++] = current;
                    current = nextGradientColour(current, colours[colour + 1], sectionSteps, step);
                }
            }
            //Fill in the transition for the last colour, looping back to the first colour.
            else {
                int[] current = colours[colours.length - 1];
                for (int step = 0; step < sectionSteps; step++) {
                    gradient[position++] = current;
                    current = nextGradientColour(current, colours[0], sectionSteps, step);
                }
            }
        }
        return gradient;
    }

    public static int[][] generateSolidLayout(int length, int[][] colours) {
        //The RGB values for the colours that make up the solid layout.
        int[][] solid = new int[length][3];
        //The number of increments for the transition between each defined colour.
        int steps = (int) Math.floor((float) length / (float) colours.length);
        //Calculate the remaining number of steps.
        int remainingSteps = length - (colours.length) * steps;
        //Fill in the layout.
        int position = 0;
        for (int colour = 0; colour < colours.length; colour++) {
            //Fill in the minimum number of positions for the section.
            for (int step = 0; step < steps; step++) {
                solid[position++] = colours[colour];
            }
            //If there are remaining unused steps, add to the current section.
            if (remainingSteps > 0) {
                solid[position++] = colours[colour];
                remainingSteps--;
            }
        }
        return solid;
    }

    public static int[][] generateAlternatingLayout(int length, int[][] colours) {
        int[][] alternating = new int[length][3];
        for (int led = 0; led < length; led += colours.length) {
            for (int colour = 0; colour < colours.length; colour++) {
                //If the layout has already been filled, stop and return the layout.
                if ((led + colour) >= length) return alternating;
                alternating[led + colour] = colours[colour];
            }
        }
        return alternating;
    }

    public static int[][] shiftLayout(int[][] layout, int offset) {
        int[][] shifted = new int[layout.length][3];
        for (int i = 0; i < layout.length; i++) {
            if (i < offset) {
                shifted[i] = layout[layout.length - 1 - offset + i];
            } else {
                shifted[i] = layout[i - offset];
            }
        }
        return shifted;
    }

    public static int[][] flipLayout(int[][] layout) {
        int[][] flipped = new int[layout.length][3];
        for (int i = 0; i < layout.length; i++) {
            flipped[layout.length - 1 - i] = layout[i];
        }
        return flipped;
    }

    public static int[][] mirrorLayout(int[][] layout) {
        int[][] mirrored = new int[layout.length][3];
        for (int i = 0; i < layout.length; i++) {
            if (i < layout.length / 2) {
                mirrored[i] = layout[i];
            } else {
                mirrored[i] = layout[layout.length - 1 - i];
            }
        }
        return mirrored;
    }

    /**
     * Ensures that the given shift value is within the given range.
     * @param shift The current shift value.
     * @param max The maximum that the shift value can be.
     * @return The same shift value if already within range, or 0 if it has exceeded the range.
     */
    public static int ensureShiftWithinRange(int shift, int max) {
        return (shift >= max) ? 0 : shift;
    }

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
