package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.main.effecthandler.Effect;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

public class OffEffect extends Effect {
    @Override
    public void tick(Settings settings, ArduinoController arduinoController, RazerChromaService razerChromaService) {
        razerChromaService.setAll(0, 0, 0);
    }
}
