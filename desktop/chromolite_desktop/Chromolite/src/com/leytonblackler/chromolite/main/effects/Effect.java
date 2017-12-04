package com.leytonblackler.chromolite.main.effects;

import com.leytonblackler.chromolite.main.Chromolite;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.utilities.LEDController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

public abstract class Effect {

    Settings settings;

    LEDController ledController;

    RazerChromaService razerChromaService;

    public void tick() {
        Chromolite model = Chromolite.getInstance();
        settings = model.getSettings();
        ledController = model.getLEDController();
        razerChromaService = model.getRazerChromaService();
    }

    protected int calculateDelay(int min, int max, int percent) {
        //Requires that the minimum delay is less than the maximum delay and the percentage is valid.
        if (min > max || percent < 0 || percent > 100) {
            throw new IllegalArgumentException();
        }
        return (int) (((((100.0 - percent) / 100.0) * (max - min))) + min);
    }

    protected void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
