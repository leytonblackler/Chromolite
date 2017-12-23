package com.leytonblackler.chromolite.main.effecthandler.effects;

import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.effecthandler.Effect;
import com.leytonblackler.chromolite.main.effecthandler.EffectUtilities;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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

    public WaveEffect(SettingsManager settings, ArduinoController arduinoController, RazerChromaService razerChromaService, LEDStripSimulationController ledStripSimulation) {
        super(settings, arduinoController, razerChromaService, ledStripSimulation);
    }

    @Override
    public void tick() {
        int[][] colours = determineColours();
        int[][] arduinoLayout, razerKeyboardLayout, razerMouseLayout, razerMousepadLayout;

        //Create the single direction wave style layouts.
        if (settings.getWaveDirection() == Direction.LEFT || settings.getWaveDirection() == Direction.RIGHT) {
            arduinoLayout = processSingleDirectionLayout(settings.getLEDStripLength(), colours, arduinoShift++);
            razerKeyboardLayout = processSingleDirectionLayout(RazerChromaService.KEYBOARD_MAX_COLUMNS, colours, razerKeyboardShift++);
            razerMouseLayout = processSingleDirectionLayout(RazerChromaService.MOUSE_MAX_ROWS, colours, razerMouseShift++);
            razerMousepadLayout = processSingleDirectionLayout(RazerChromaService.MOUSEPAD_MAX_LEDS, colours, razerMousepadShift++);

            System.out.println(arduinoShift);

            arduinoShift = ensureShiftWithinRange(arduinoShift, settings.getLEDStripLength());
            razerKeyboardShift = ensureShiftWithinRange(razerKeyboardShift, RazerChromaService.KEYBOARD_MAX_COLUMNS);
            razerMouseShift = ensureShiftWithinRange(razerMouseShift, RazerChromaService.MOUSE_MAX_ROWS);
            razerMousepadShift = ensureShiftWithinRange(razerMousepadShift, RazerChromaService.MOUSEPAD_MAX_LEDS);
        }

        //Create the split wave style layouts.
        else {
            //
            throw new IllegalStateException("Temporary illegal state.");
        }

        setLEDSimulation(arduinoLayout);
        setRazerChroma(colours[0], razerKeyboardLayout, razerMouseLayout, razerMousepadLayout);

        //Calculate how long to wait before the next tick.
        int time = EffectUtilities.calculateDelay(30, 100, settings.getSpeed());
        delay(time);
    }

    private int ensureShiftWithinRange(int shift, int max) {
        return (shift >= max) ? 0 : shift;
    }

    private int[][] processSingleDirectionLayout(int length, int[][] colours, int shift) {
        int[][] layout = EffectUtilities.generateGradientLayout(length, colours);
        layout = EffectUtilities.shiftLayout(layout, shift);
        if (settings.getWaveDirection() == Direction.LEFT) {
            layout = EffectUtilities.flipLayout(layout);
        }
        return layout;
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
