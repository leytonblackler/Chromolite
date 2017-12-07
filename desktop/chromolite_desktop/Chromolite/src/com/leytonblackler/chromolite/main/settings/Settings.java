package com.leytonblackler.chromolite.main.settings;

import com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Settings implements Serializable {

    public enum Mode {
        STATIC,
        RANDOM,
        WAVE,
        MUSIC,
        CYCLE,
        STROBE,
        SCAN,
        OFF
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

    private boolean syncWithRazer = DefaultSettings.SYNC_WITH_RAZER;

    private boolean razerChromaEnabled = DefaultSettings.RAZER_CHROMA_ENABLED;

    private String ip = DefaultSettings.IP;

    private int port = DefaultSettings.PORT;

    /*
    Observers
     */

    private Set<SettingsObserver> observers = new HashSet<>();

    public void addObserver(SettingsObserver observer) {
        observers.add(observer);
    }

    public void clearObservers() {
        observers.clear();
    }

    public void notifyObservers() {
        observers.forEach((observers) -> observers.update(this));
    }

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

    public boolean syncWithRazer() {
        return syncWithRazer;
    }

    public boolean razerChromaEnabled() {
        return razerChromaEnabled;
    }

    public String getIP() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    /*
    Mutator Methods
     */

    public void setColourSelector(ColourSelector colourSelector) {
        this.colourSelector = colourSelector;
        observers.forEach((observer) -> observer.updateSpectrum(this));
        observers.forEach((observer) -> observer.updateColours(this));
    }

    public void setPrimaryColour(int[] primaryColour) {
        this.primaryColour = primaryColour;
        observers.forEach((observer) -> observer.updateSpectrum(this));
        observers.forEach((observer) -> observer.updateColours(this));
    }

    public void setSecondaryColour(int[] secondaryColour) {
        this.secondaryColour = secondaryColour;
        observers.forEach((observer) -> observer.updateSpectrum(this));
        observers.forEach((observer) -> observer.updateColours(this));
    }

    public void setTertiaryColour(int[] tertiaryColour) {
        this.tertiaryColour = tertiaryColour;
        observers.forEach((observer) -> observer.updateSpectrum(this));
        observers.forEach((observer) -> observer.updateColours(this));
    }

    public void setMode(Mode mode) {
        this.mode = mode;
        observers.forEach((observer) -> observer.updateModes(this));
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setSpeed(int speed) {
        this.speed = speed;
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setCycleNumberOfColours(CycleEffect.NumberOfColours cycleNumberOfColours) {
        this.cycleNumberOfColours = cycleNumberOfColours;
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setSyncWithRazer(boolean syncWithRazer) {
        this.syncWithRazer = syncWithRazer;
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setRazerChromaEnabled(boolean razerChromaEnabled) {
        this.razerChromaEnabled = razerChromaEnabled;
        observers.forEach((observer) -> observer.updateGeneralOptions(this));
    }

    public void setIp(String ip) {
        this.ip = ip;
        observers.forEach((observer) -> observer.updateAndroidAppConnection(this));
    }

    public void setPort(int port) {
        this.port = port;
        observers.forEach((observer) -> observer.updateAndroidAppConnection(this));
    }


}
