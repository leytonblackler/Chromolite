package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.main.effecthandler.EffectUtilities;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;

import java.util.List;

public class RandomEffect extends Effect {

    private static final int MAX_DELAY = 1800;
    private static final int MIN_DELAY = 200;

    private int colour[] = new int[3];

    public RandomEffect(LightSettings lightSettings) {
        super(lightSettings);
    }

    @Override
    public void tick(EffectPlatform ... effectPlatforms) {
        colour[0] = randomInt();
        colour[1] = randomInt();
        colour[2] = randomInt();

        //razerChromaService.setSingleDevices(colour[0], colour[1], colour[2]);
        //ledStripSimulation.setAll(colour[0], colour[1], colour[2]);

        //Calculate how long to wait before the next tick.
        int time = EffectUtilities.calculateDelay(MIN_DELAY, MAX_DELAY, lightSettings.getSpeed());

        delay(time);
    }

    private int randomInt() {
        return (int) (Math.random() * ((255 - 0) + 1)) + 0;
    }
}
