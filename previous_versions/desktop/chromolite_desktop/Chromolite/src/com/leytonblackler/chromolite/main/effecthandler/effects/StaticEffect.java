package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.main.effecthandler.EffectUtilities;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;

import java.util.ArrayList;
import java.util.List;

public class StaticEffect extends Effect {

    public StaticEffect(LightSettings lightSettings) {
        super(lightSettings);
    }

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
    public void tick(EffectPlatform ... effectPlatforms) {
        int[][] colours = determineColours();
        for (int platform = 0; platform < effectPlatforms.length; platform++) {
            List<int[][]> layouts = new ArrayList();
            for (int i = 0; i < effectPlatforms[platform].getLengths().length; i++) {
                int[][] layout;
                switch (lightSettings.getStaticStyle()) {
                    default:
                    case SOLID:
                        layout = EffectUtilities.generateSolidLayout(effectPlatforms[platform].getLengths()[i], colours);
                        break;
                    case GRADIENT:
                        layout = EffectUtilities.generateGradientLayout(effectPlatforms[platform].getLengths()[i], colours);
                        break;
                    case ALTERNATING:
                        layout = EffectUtilities.generateAlternatingLayout(effectPlatforms[platform].getLengths()[i], colours);
                        break;
                }
                layouts.add(layout);
            }
            effectPlatforms[platform].setLayouts(layouts);
        }
        delay(100);
    }

    protected int[][] determineColours() {
        int[][] colours = new int[0][0];
        switch (lightSettings.getStaticNumberOfColours()) {
            case ONE:
                colours = new int[1][3];
                colours[0] = lightSettings.getPrimaryColour();
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
            case SPECTRUM:
                colours = EffectUtilities.SPECTRUM_COLOURS;
                break;
        }
        return colours;
    }

}
