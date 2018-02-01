package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.main.effecthandler.EffectUtilities;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WaveEffect extends Effect {

    public enum NumberOfColours {
        TWO,
        THREE,
        SPECTRUM
    }

    public enum Direction {
        LEFT,
        RIGHT,
        INWARDS,
        OUTWARDS
    }

    private Map<EffectPlatform, int[]> shifts = new HashMap<>();

    public WaveEffect(LightSettings lightSettings) {
        super(lightSettings);
    }

    @Override
    public void tick(EffectPlatform ... effectPlatforms) {
        int[][] colours = determineColours();
        initialiseShifts(effectPlatforms);
        for (int platform = 0; platform < effectPlatforms.length; platform++) {
            List<int[][]> layouts = new ArrayList();
            for (int i = 0; i < effectPlatforms[platform].getLengths().length; i++) {
                //Get the current shift for the effect platform.
                int shift = shifts.get(effectPlatforms[platform])[i];
                //Process the layout for the effect platform.
                int[][] layout = processLayout(effectPlatforms[platform].getLengths()[i], colours, shift);
                //Set the new shift for the effect platform.
                shift = EffectUtilities.ensureShiftWithinRange(++shift, effectPlatforms[platform].getLengths()[i]);
                shifts.get(effectPlatforms[platform])[i] = shift;
                layouts.add(layout);
            }
            effectPlatforms[platform].setLayouts(layouts);
        }

        //Calculate how long to wait before the next tick.
        int time = EffectUtilities.calculateDelay(30, 100, lightSettings.getSpeed());
        delay(time);
    }

    private void initialiseShifts(EffectPlatform ... effectPlatforms) {
        for (EffectPlatform effectPlatform : effectPlatforms) {
            if (!shifts.containsKey(effectPlatform)) {
                shifts.put(effectPlatform, new int[effectPlatform.getLengths().length]);
            }
        }
    }

    private int[][] processLayout(int length, int[][] colours, int shift) {
        int[][] layout = EffectUtilities.generateGradientLayout(length, colours);
        layout = EffectUtilities.shiftLayout(layout, shift);
        if (lightSettings.getWaveDirection() == Direction.LEFT || lightSettings.getWaveDirection() == Direction.OUTWARDS) {
            layout = EffectUtilities.flipLayout(layout);
        }
        if (lightSettings.getWaveDirection() == Direction.INWARDS || lightSettings.getWaveDirection() == Direction.OUTWARDS) {
            layout = EffectUtilities.mirrorLayout(layout);
        }
        return layout;
    }

    protected int[][] determineColours() {
        int[][] colours = new int[0][0];
        switch (lightSettings.getWaveNumberOfColours()) {
            case SPECTRUM:
                colours = EffectUtilities.SPECTRUM_COLOURS;
                break;
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
        }
        return colours;
    }
}
