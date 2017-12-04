package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.main.effecthandler.Effect;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

public class RandomEffect extends Effect {

    private static final int MAX_DELAY = 2000;
    private static final int MIN_DELAY = 200;

    private int colour[] = new int[3];

    @Override
    public void tick(Settings settings, ArduinoController arduinoController, RazerChromaService razerChromaService) {
        colour[0] = randomInt();
        colour[1] = randomInt();
        colour[2] = randomInt();

        razerChromaService.setAll(colour[0], colour[1], colour[2]);

        //Calculate how long to wait before the next tick.
        int time = calculateDelay(MIN_DELAY, MAX_DELAY, settings.getSpeed());

        delay(time);
    }

    private int randomInt() {
        return (int) (Math.random() * ((255 - 0) + 1)) + 0;
    }
}
