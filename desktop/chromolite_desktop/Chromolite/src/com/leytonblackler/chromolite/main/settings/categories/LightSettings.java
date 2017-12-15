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

    private int[] primaryIndicatorPosition = DefaultSettings.PRIMARY_INDICATOR_POSITION;
    private int[] secondaryIndicatorPosition = DefaultSettings.SECONDARY_INDICATOR_POSITION;
    private int[] tertiaryIndicatorPosition = DefaultSettings.TERTIARY_INDICATOR_POSITION;

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

    public int[] getPrimaryIndicatorPosition() {
        return primaryIndicatorPosition;
    }

    public int[] getSecondaryIndicatorPosition() {
        return secondaryIndicatorPosition;
    }

    public int[] getTertiaryIndicatorPosition() {
        return tertiaryIndicatorPosition;
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

    public void setPrimaryIndicatorPosition(int x, int y) {
        this.primaryIndicatorPosition = new int[2];
        this.primaryIndicatorPosition[0] = x;
        this.primaryIndicatorPosition[1] = y;
    }

    public void setSecondaryIndicatorPosition(int x, int y) {
        this.secondaryIndicatorPosition = new int[2];
        this.secondaryIndicatorPosition[0] = x;
        this.secondaryIndicatorPosition[1] = y;
    }

    public void setTertiaryIndicatorPosition(int x, int y) {
        this.tertiaryIndicatorPosition = new int[2];
        this.tertiaryIndicatorPosition[0] = x;
        this.tertiaryIndicatorPosition[1] = y;
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
