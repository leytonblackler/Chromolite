package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;

import java.util.List;

public class OffEffect extends Effect {

    public OffEffect(LightSettings lightSettings) {
        super(lightSettings);
    }

    @Override
    public void tick(EffectPlatform ... effectPlatforms) {
        //razerChromaService.setSingleDevices(0, 0, 0);
        //ledStripSimulation.setAll(0, 0, 0);
    }
}
