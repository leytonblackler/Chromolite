package com.leytonblackler.chromolite.main.settings.categories;

import com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect;
import com.leytonblackler.chromolite.main.settings.presets.DefaultSettings;

public class LightSettings {

    public enum Mode {
        STATIC,
        CYCLE,
        WAVE,
        MUSIC,
        SCAN,
        STROBE,
        OFF,
        DISABLE
    }

    public enum ColourSelector {
        PRIMARY,
        SECONDARY,
        TERTIARY
    }

    private ColourSelector colourSelector = DefaultSettings.COLOUR_SELECTOR;

    private int[] primaryColour = DefaultSettings.PRIMARY_COLOUR;

    private int[] secondaryColour = DefaultSettings.SECONDARY_COLOUR;

    private int[] tertiaryColour = DefaultSettings.TERTIARY_COLOUR;

    private Mode mode = DefaultSettings.MODE;

    private int brightness = DefaultSettings.BRIGHTNESS;

    private int speed = DefaultSettings.SPEED;

    private CycleEffect.NumberOfColours cycleNumberOfColours = DefaultSettings.CYCLE_NUMBER_OF_COLOURS;

    private CycleEffect.Transition cycleTransition = DefaultSettings.CYCLE_TRANSITION;

    /*
    Accessor Methods
     */

    public ColourSelector getColourSelector() {
        return colourSelector;
    }

    public int[] getPrimaryColour() {
        return primaryColour;
    }

    public int[] getSecondaryColour() {
        return secondaryColour;
    }

    public int[] getTertiaryColour() {
        return tertiaryColour;
    }

    public Mode getMode() {
        return mode;
    }

    public int getBrightness() {
        return brightness;
    }

    public int getSpeed() {
        return speed;
    }

    public CycleEffect.NumberOfColours getCycleNumberOfColours() {
        return cycleNumberOfColours;
    }

    public CycleEffect.Transition getCycleTransition() {
        return cycleTransition;
    }

    /*
    Mutator Methods
     */

    public void setColourSelector(ColourSelector colourSelector) {
        this.colourSelector = colourSelector;
    }

    public void setPrimaryColour(int[] primaryColour) {
        this.primaryColour = primaryColour;
    }

    public void setSecondaryColour(int[] secondaryColour) {
        this.secondaryColour = secondaryColour;
    }

    public void setTertiaryColour(int[] tertiaryColour) {
        this.tertiaryColour = tertiaryColour;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setCycleNumberOfColours(CycleEffect.NumberOfColours cycleNumberOfColours) {
        this.cycleNumberOfColours = cycleNumberOfColours;
    }

    public void setCycleTransition(CycleEffect.Transition cycleTransition) {
        this.cycleTransition = cycleTransition;
    }

}
