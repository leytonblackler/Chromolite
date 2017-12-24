package com.leytonblackler.chromolite.main.effecthandler.effectplatforms;

import com.leytonblackler.chromolite.main.model.Model;
import com.leytonblackler.chromolite.main.settings.presets.DefaultSettings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

import java.util.List;

public class ArduinoEffectPlatform implements EffectPlatform {

    private static final int[] LENGTHS = {
            DefaultSettings.LED_STRIP_LENGTH
    };

    private ArduinoController arduinoController;

    public ArduinoEffectPlatform(ArduinoController arduinoController) {
        this.arduinoController = arduinoController;
    }

    @Override
    public void setLayouts(List<int[][]> layouts) {
        //TEMPORARY
        Model.ledStripSim.setLayout(layouts.get(0));
    }

    @Override
    public int[] getLengths() {
        return LENGTHS;
    }
}
