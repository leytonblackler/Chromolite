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
        int[] primary = settings.getPrimaryColour();
        int[] secondary = settings.getSecondaryColour();
        int[] tertiary = settings.getTertiaryColour();
        razerChromaService.setAll(primary[0], primary[1], primary[2]);

        //setThree(settings, ledStripSimulation, primary, secondary, tertiary);
        //setSolid(settings, ledStripSimulation);
        int[][] layout = new int[settings.getLEDStripLength()][3];
        int[][] colours = generateColours(settings);
        switch (settings.getStaticStyle()) {
            case SOLID:
                layout = EffectUtilities.generateSolid(settings.getLEDStripLength(), colours);
                break;
            case GRADIENT:
                layout = EffectUtilities.generateGradient(settings.getLEDStripLength(), colours);
                break;
            case ALTERNATING:
                layout = EffectUtilities.generateAlternating(settings.getLEDStripLength(), colours);
                break;
        }
        setLayout(ledStripSimulation, layout);

        delay(100);
    }

    private int[][] generateColours(SettingsManager settings) {
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

    private void setLayout(LEDStripSimulationController ledStripSimulation, int[][] layout) {
        for (int led = 0; led < layout.length; led++) {
            ledStripSimulation.setLED(led, layout[led][0], layout[led][1], layout[led][2]);
        }
    }

}
