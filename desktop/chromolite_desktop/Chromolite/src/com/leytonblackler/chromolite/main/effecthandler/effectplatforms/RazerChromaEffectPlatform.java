package com.leytonblackler.chromolite.main.effecthandler.effectplatforms;

import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

import java.util.List;

public class RazerChromaEffectPlatform implements EffectPlatform {

    private static final int[] LENGTHS = {
        RazerChromaService.KEYBOARD_MAX_COLUMNS, RazerChromaService.MOUSE_MAX_ROWS, RazerChromaService.MOUSEPAD_MAX_LEDS
    };

    private RazerChromaService razerChromaService;

    public RazerChromaEffectPlatform(RazerChromaService razerChromaService) {
        this.razerChromaService = razerChromaService;
    }

    @Override
    public void setLayouts(List<int[][]> layouts) {
        razerChromaService.setSingleDevices(layouts.get(0)[0][0], layouts.get(0)[0][1], layouts.get(0)[0][2]);
        razerChromaService.setKeyboardLayout(layouts.get(0));
        razerChromaService.setMouseLayout(layouts.get(1));
        razerChromaService.setMousepadLayout(layouts.get(2));
    }

    @Override
    public int[] getLengths() {
        return LENGTHS;
    }
}
