package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.effecthandler.Effect;
import com.leytonblackler.chromolite.main.effecthandler.EffectUtilities;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

public class WaveEffect extends Effect {

    public WaveEffect(SettingsManager settings, ArduinoController arduinoController, RazerChromaService razerChromaService, LEDStripSimulationController ledStripSimulation) {
        super(settings, arduinoController, razerChromaService, ledStripSimulation);
    }

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

    @Override
    public void tick() {
        int[][] colours = determineColours();
        int[][] arduinoLayout, razerKeyboardLayout, razerMouseLayout, razerMousepadLayout;

        arduinoLayout = EffectUtilities.shiftLayout(EffectUtilities.generateGradientLayout(settings.getLEDStripLength(), colours), arduinoShift++);
        razerKeyboardLayout = EffectUtilities.shiftLayout(EffectUtilities.generateGradientLayout(RazerChromaService.KEYBOARD_MAX_COLUMNS, colours), razerKeyboardShift++);
        razerMouseLayout = EffectUtilities.shiftLayout(EffectUtilities.generateGradientLayout(RazerChromaService.MOUSE_MAX_ROWS, colours), razerMouseShift++);
        razerMousepadLayout = EffectUtilities.shiftLayout(EffectUtilities.generateGradientLayout(RazerChromaService.MOUSEPAD_MAX_LEDS, colours), razerMousepadShift++);

        if (arduinoShift >= settings.getLEDStripLength()) {
            arduinoShift = 0;
        }

        if (razerKeyboardShift >= RazerChromaService.KEYBOARD_MAX_COLUMNS) {
            razerKeyboardShift = 0;
        }

        if (razerMouseShift >= RazerChromaService.MOUSE_MAX_ROWS) {
            razerMouseShift = 0;
        }

        if (razerMousepadShift >= RazerChromaService.MOUSEPAD_MAX_LEDS) {
            razerMousepadShift = 0;
        }

        /*switch (settings.getStaticStyle()) {
            default:
            case SOLID:
                arduinoLayout = EffectUtilities.generateSolidLayout(settings.getLEDStripLength(), colours);
                razerKeyboardLayout = EffectUtilities.generateSolidLayout(RazerChromaService.KEYBOARD_MAX_COLUMNS, colours);
                razerMouseLayout = EffectUtilities.generateSolidLayout(RazerChromaService.MOUSE_MAX_ROWS, colours);
                razerMousepadLayout = EffectUtilities.generateSolidLayout(RazerChromaService.MOUSEPAD_MAX_LEDS, colours);
                break;
            case GRADIENT:
                arduinoLayout = EffectUtilities.generateGradientLayout(settings.getLEDStripLength(), colours);
                razerKeyboardLayout = EffectUtilities.generateGradientLayout(RazerChromaService.KEYBOARD_MAX_COLUMNS, colours);
                razerMouseLayout = EffectUtilities.generateGradientLayout(RazerChromaService.MOUSE_MAX_ROWS, colours);
                razerMousepadLayout = EffectUtilities.generateGradientLayout(RazerChromaService.MOUSEPAD_MAX_LEDS, colours);
                break;
            case ALTERNATING:
                arduinoLayout = EffectUtilities.generateAlternatingLayout(settings.getLEDStripLength(), colours);
                razerKeyboardLayout = EffectUtilities.generateAlternatingLayout(RazerChromaService.KEYBOARD_MAX_COLUMNS, colours);
                razerMouseLayout = EffectUtilities.generateAlternatingLayout(RazerChromaService.MOUSE_MAX_ROWS, colours);
                razerMousepadLayout = EffectUtilities.generateAlternatingLayout(RazerChromaService.MOUSEPAD_MAX_LEDS, colours);
                break;
        }*/

        setLEDSimulation(arduinoLayout);
        setRazerChroma(colours[0], razerKeyboardLayout, razerMouseLayout, razerMousepadLayout);


        //Calculate how long to wait before the next tick.
        int time = EffectUtilities.calculateDelay(30, 100, settings.getSpeed());
        delay(time);
    }

    protected int[][] determineColours() {
        int[][] colours = new int[0][0];
        switch (settings.getWaveNumberOfColours()) {
            case SPECTRUM:
                colours = EffectUtilities.SPECTRUM_COLOURS;
                break;
            case TWO:
                colours = new int[2][3];
                colours[0] = settings.getPrimaryColour();
                colours[1] = settings.getSecondaryColour();
                break;
            case THREE:
                colours = new int[3][3];
                colours[0] = settings.getPrimaryColour();
                colours[1] = settings.getSecondaryColour();
                colours[2] = settings.getTertiaryColour();
                break;
        }
        return colours;
    }
}
