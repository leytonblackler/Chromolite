package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;

public abstract class Effect {

    protected LightSettings lightSettings;

    public Effect(LightSettings lightSettings) {
        this.lightSettings = lightSettings;
    }

    public abstract void tick(EffectPlatform ... effectPlatforms);

    protected void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
