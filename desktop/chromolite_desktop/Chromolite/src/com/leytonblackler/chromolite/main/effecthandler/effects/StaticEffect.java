package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.effecthandler.Effect;
import com.leytonblackler.chromolite.main.effecthandler.EffectUtilities;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

public class StaticEffect extends Effect {

    public enum Style {
        SOLID,
        GRADIENT,
        ALTERNATING
    }

    public enum NumberOfColours {
        ONE,
        TWO,
        THREE,
        SPECTRUM
    }

    @Override
    public void tick(SettingsManager settings, ArduinoController arduinoController, RazerChromaService razerChromaService, LEDStripSimulationController ledStripSimulation) {
        int[][] colours = determineColours(settings);
        int[][] arduinoLayout, razerKeyboardLayout, razerMouseLayout, razerMousepadLayout;
        switch (settings.getStaticStyle()) {
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
        }

        ledStripSimulation.setLayout(arduinoLayout);
        razerChromaService.setSingleDevices(colours[0][0], colours[0][1], colours[0][2]);
        razerChromaService.setKeyboardLayout(razerKeyboardLayout);
        razerChromaService.setMouseLayout(razerMouseLayout);
        razerChromaService.setMousepadLayout(razerMousepadLayout);

        delay(100);
    }

    private int[][] determineColours(SettingsManager settings) {
        int[][] colours = new int[0][0];
        switch (settings.getStaticNumberOfColours()) {
            case ONE:
                colours = new int[1][3];
                colours[0] = settings.getPrimaryColour();
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
            case SPECTRUM:
                colours = EffectUtilities.SPECTRUM_COLOURS;
                break;
        }
        return colours;
    }

    /*private void setLayout(LEDStripSimulationController ledStripSimulation, int[][] layout) {
        for (int led = 0; led < layout.length; led++) {
            ledStripSimulation.setLED(led, layout[led][0], layout[led][1], layout[led][2]);
        }
    }*/

}
