package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.main.effecthandler.EffectUtilities;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;

import java.util.ArrayList;
import java.util.List;

public class OffEffect extends Effect {

    public OffEffect(LightSettings lightSettings) {
        super(lightSettings);
    }

    @Override
    public void tick(EffectPlatform ... effectPlatforms) {
        for (int platform = 0; platform < effectPlatforms.length; platform++) {
            List<int[][]> layouts = new ArrayList();
            for (int i = 0; i < effectPlatforms[platform].getLengths().length; i++) {
                int[][] layout = EffectUtilities.generateSolidLayout(effectPlatforms[platform].getLengths()[i], EffectUtilities.BLACK);
                layouts.add(layout);
            }
            effectPlatforms[platform].setLayouts(layouts);
        }
        delay(100);
    }
}
