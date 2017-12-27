package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.main.effecthandler.EffectUtilities;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;

import java.util.List;

public class WaveEffect extends Effect {

    public enum NumberOfColours {
        TWO,
        THREE,
        SPECTRUM
    }

    public enum Direction {
        LEFT,
        RIGHT,
        INWARDS,
        OUTWARDS
    }

    private int arduinoShift = 0;
    private int razerKeyboardShift = 0;
    private int razerMouseShift = 0;
    private int razerMousepadShift = 0;

    public WaveEffect(LightSettings lightSettings) {
        super(lightSettings);
    }

    @Override
    public void tick(EffectPlatform ... effectPlatforms) {
        int[][] colours = determineColours();
        /*int[][] arduinoLayout, razerKeyboardLayout, razerMouseLayout, razerMousepadLayout;

        arduinoLayout = processLayout(lightSettings.getLEDStripLength(), colours, arduinoShift++);
        razerKeyboardLayout = processLayout(RazerChromaService.KEYBOARD_MAX_COLUMNS, colours, razerKeyboardShift++);
        razerMouseLayout = processLayout(RazerChromaService.MOUSE_MAX_ROWS, colours, razerMouseShift++);
        razerMousepadLayout = processLayout(RazerChromaService.MOUSEPAD_MAX_LEDS, colours, razerMousepadShift++);

        arduinoShift = EffectUtilities.ensureShiftWithinRange(arduinoShift, lightSettings.getLEDStripLength());
        razerKeyboardShift = EffectUtilities.ensureShiftWithinRange(razerKeyboardShift, RazerChromaService.KEYBOARD_MAX_COLUMNS);
        razerMouseShift = EffectUtilities.ensureShiftWithinRange(razerMouseShift, RazerChromaService.MOUSE_MAX_ROWS);
        razerMousepadShift = EffectUtilities.ensureShiftWithinRange(razerMousepadShift, RazerChromaService.MOUSEPAD_MAX_LEDS);

        PlatformSettings.Platform platform = lightSettings.getPlatform();

        setLEDSimulation(arduinoLayout);

        if (platform == PlatformSettings.Platform.ARDUINO || lightSettings.getSyncPlatforms()) {
            //setArduino(arduinoLayout);
        }

        if (platform == PlatformSettings.Platform.RAZER_CHROMA || lightSettings.getSyncPlatforms()) {
            setRazerChroma(razerKeyboardLayout[0], razerKeyboardLayout, razerMouseLayout, razerMousepadLayout);
        }

        if (platform == PlatformSettings.Platform.RAZER_CHROMA || lightSettings.getSyncPlatforms()) {
            //setPhilipsHue(philipsHueLayout);
        }*/

        //Calculate how long to wait before the next tick.
        int time = EffectUtilities.calculateDelay(30, 100, lightSettings.getSpeed());
        delay(time);
    }

    private int[][] processLayout(int length, int[][] colours, int shift) {
        int[][] layout = EffectUtilities.generateGradientLayout(length, colours);
        layout = EffectUtilities.shiftLayout(layout, shift);
        if (lightSettings.getWaveDirection() == Direction.LEFT || lightSettings.getWaveDirection() == Direction.OUTWARDS) {
            layout = EffectUtilities.flipLayout(layout);
        }
        if (lightSettings.getWaveDirection() == Direction.INWARDS || lightSettings.getWaveDirection() == Direction.OUTWARDS) {
            layout = EffectUtilities.mirrorLayout(layout);
        }
        return layout;
    }

    protected int[][] determineColours() {
        int[][] colours = new int[0][0];
        switch (lightSettings.getWaveNumberOfColours()) {
            case SPECTRUM:
                colours = EffectUtilities.SPECTRUM_COLOURS;
                break;
            case TWO:
                colours = new int[2][3];
                colours[0] = lightSettings.getPrimaryColour();
                colours[1] = lightSettings.getSecondaryColour();
                break;
            case THREE:
                colours = new int[3][3];
                colours[0] = lightSettings.getPrimaryColour();
                colours[1] = lightSettings.getSecondaryColour();
                colours[2] = lightSettings.getTertiaryColour();
                break;
        }
        return colours;
    }
}
