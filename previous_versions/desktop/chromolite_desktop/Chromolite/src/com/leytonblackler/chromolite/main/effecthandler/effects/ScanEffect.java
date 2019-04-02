package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.main.effecthandler.EffectUtilities;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;
import com.leytonblackler.chromolite.main.settings.presets.DefaultSettings;

import java.util.ArrayList;
import java.util.List;

public class ScanEffect extends Effect {

    public enum Size {
        NARROW,
        MEDIUM,
        WIDE
    }

    private final int NARROW_SIZE_PERCENT = 5;
    private final int MEDIUM_SIZE_PERCENT = 15;
    private final int WIDE_SIZE_PERCENT = 50;

    private final int POSITION_CHANGE_PERCENT = 5;

    private double positionChange = POSITION_CHANGE_PERCENT;

    private double positionPercent = 0;

    public ScanEffect(LightSettings lightSettings) {
        super(lightSettings);
    }

    @Override
    public void tick(EffectPlatform ... effectPlatforms) {
        int[][] colours = determineColours();
        for (int platform = 0; platform < effectPlatforms.length; platform++) {
            List<int[][]> layouts = new ArrayList();
            for (int i = 0; i < effectPlatforms[platform].getLengths().length; i++) {
                //Process the layout for the effect platform.
                int[][] layout = processLayout(effectPlatforms[platform].getLengths()[i], positionPercent, colours);
                layouts.add(layout);
            }
            effectPlatforms[platform].setLayouts(layouts);
        }

        if (positionPercent == 0) {
            positionChange = POSITION_CHANGE_PERCENT;
        } else if (positionPercent == 100) {
            positionChange = -POSITION_CHANGE_PERCENT;
        }
        positionPercent += positionChange;

        //Calculate how long to wait before the next tick.
        int time = EffectUtilities.calculateDelay(30, 100, lightSettings.getSpeed());
        delay(time);
    }

    private int[][] processLayout(int length, double positionPercent, int[][] colours) {

        int sizePercent = NARROW_SIZE_PERCENT;

        switch (lightSettings.getScanSize()) {
            case NARROW:
                sizePercent = NARROW_SIZE_PERCENT;
                break;
            case MEDIUM:
                sizePercent = MEDIUM_SIZE_PERCENT;
                break;
            case WIDE:
                sizePercent = WIDE_SIZE_PERCENT;
                break;
        }

        int width = calculateWidth(length, sizePercent);
        int maxIndex = length - width;
        int start = (int) (maxIndex * (positionPercent / 100));
        int end = start + width;

        //Create a base for the layout (background colour).
        int[][] base = EffectUtilities.generateSolidLayout(length, colours[1]);
        //Add the scan line on top of the base layout.
        int[][] layout = EffectUtilities.setRange(base, start, end, colours[0]);

        return layout;
    }

    private int calculateWidth(int length, double sizePercent) {
        //Calculate the size of the scan line based on the width, rounding down.
        int width = (int) (length * (sizePercent / 100));
        //Ensure there is a minimum width of 1.
        if (width < 1) {
            width = 1;
        }
        return width;
    }

    protected int[][] determineColours() {
        int[][] colours = new int[2][3];
        colours[0] = lightSettings.getPrimaryColour();
        if (lightSettings.getScanBackground()) {
            colours[1] = lightSettings.getSecondaryColour();
        } else {
            colours[1] = EffectUtilities.BLACK;
        }
        return colours;
    }
}
