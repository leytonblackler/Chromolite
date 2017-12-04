package com.leytonblackler.chromolite.main.effecthandler;

import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

public abstract class Effect {

    public abstract void tick(Settings settings, ArduinoController arduinoController, RazerChromaService razerChromaService);

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
