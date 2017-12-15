package com.leytonblackler.chromolite.main.settings;

import com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect;
import com.leytonblackler.chromolite.main.settings.categories.AppConnectSettings;
import com.leytonblackler.chromolite.main.settings.categories.GeneralSettings;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;
import com.leytonblackler.chromolite.main.settings.categories.PlatformSettings;

import java.util.HashSet;
import java.util.Set;

public class SettingsManager {

    private LightSettings arduinoSettings;

    private LightSettings razerChromaSettings;

    private LightSettings phillipsHueSettings;

    private PlatformSettings platformSettings;

    private GeneralSettings generalSettings;

    private AppConnectSettings appConnectSettings;

    public SettingsManager() {
        arduinoSettings = new LightSettings();
        razerChromaSettings = new LightSettings();
        phillipsHueSettings = new LightSettings();
        platformSettings = new PlatformSettings();
        generalSettings = new GeneralSettings();
        appConnectSettings = new AppConnectSettings();
    }

    private LightSettings currentLightSettings() {
        switch (platformSettings.getPlatform()) {
            case ARDUINO:
                return arduinoSettings;
            case RAZER_CHROMA:
                return razerChromaSettings;
            case PHILLIPS_HUE:
                return phillipsHueSettings;
        }
        throw new IllegalStateException();
    }

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

    public LightSettings getArduinoSettings() {
        return arduinoSettings;
    }

    public LightSettings getRazerChromaSettings() {
        return razerChromaSettings;
    }

    public LightSettings getPhillipsHueSettings() {
        return phillipsHueSettings;
    }

    public int getLEDStripLength() {
        return generalSettings.getLEDStripLength();
    }

    public LightSettings.ColourSelector getColourSelector() {
        return currentLightSettings().getColourSelector();
    }

    public int[] getPrimaryColour() {
        return currentLightSettings().getPrimaryColour();
    }

    public int[] getSecondaryColour() {
        return currentLightSettings().getSecondaryColour();
    }

    public int[] getTertiaryColour() {
        return currentLightSettings().getTertiaryColour();
    }

    public int[] getPrimaryIndicatorPosition() {
        return currentLightSettings().getPrimaryIndicatorPosition();
    }

    public int[] getSecondaryIndicatorPosition() {
        return currentLightSettings().getSecondaryIndicatorPosition();
    }

    public int[] getTertiaryIndicatorPosition() {
        return currentLightSettings().getTertiaryIndicatorPosition();
    }

    public LightSettings.Mode getMode() {
        return currentLightSettings().getMode();
    }

    public int getBrightness() {
        return currentLightSettings().getBrightness();
    }

    public int getSpeed() {
        return currentLightSettings().getSpeed();
    }

    public CycleEffect.NumberOfColours getCycleNumberOfColours() {
        return currentLightSettings().getCycleNumberOfColours();
    }

    public CycleEffect.Transition getCycleTransition() {
        return currentLightSettings().getCycleTransition();
    }

    public PlatformSettings.Platform getPlatform() {
        return platformSettings.getPlatform();
    }

    public boolean getSyncPlatforms() {
        return platformSettings.getSyncPlatforms();
    }

    /*public String getIP() {
        return ip;
    }

    public int getPort() {
        return port;
    }*/

    /*
    Mutator Methods
     */

    public void setLEDStripLength(int ledStripLength) {
        generalSettings.setLEDStripLength(ledStripLength);
    }

    public void setColourSelector(LightSettings.ColourSelector colourSelector) {
        currentLightSettings().setColourSelector(colourSelector);
        observers.forEach((observer) -> observer.updateColours(this));
    }

    public void setPrimaryColour(int[] primaryColour) {
        currentLightSettings().setPrimaryColour(primaryColour);
        observers.forEach((observer) -> observer.updateColours(this));
        observers.forEach((observer) -> observer.updateSpectrum(this));
    }

    public void setSecondaryColour(int[] secondaryColour) {
        currentLightSettings().setSecondaryColour(secondaryColour);
        observers.forEach((observer) -> observer.updateColours(this));
        observers.forEach((observer) -> observer.updateSpectrum(this));
    }

    public void setTertiaryColour(int[] tertiaryColour) {
        currentLightSettings().setTertiaryColour(tertiaryColour);
        observers.forEach((observer) -> observer.updateColours(this));
        observers.forEach((observer) -> observer.updateSpectrum(this));
    }

    public void setPrimaryIndicatorPosition(int x, int y) {
        currentLightSettings().setPrimaryIndicatorPosition(x, y);
        observers.forEach((observer) -> observer.updateSpectrum(this));
    }

    public void setSecondaryIndicatorPosition(int x, int y) {
        currentLightSettings().setSecondaryIndicatorPosition(x, y);
        observers.forEach((observer) -> observer.updateSpectrum(this));
    }

    public void setTertiaryIndicatorPosition(int x, int y) {
        currentLightSettings().setTertiaryIndicatorPosition(x, y);
        observers.forEach((observer) -> observer.updateSpectrum(this));
    }

    public void setMode(LightSettings.Mode mode) {
        currentLightSettings().setMode(mode);
        observers.forEach((observer) -> observer.updateModes(this));
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setBrightness(int brightness) {
        currentLightSettings().setBrightness(brightness);
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setSpeed(int speed) {
        currentLightSettings().setSpeed(speed);
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setCycleNumberOfColours(CycleEffect.NumberOfColours cycleNumberOfColours) {
        currentLightSettings().setCycleNumberOfColours(cycleNumberOfColours);
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setCycleTransition(CycleEffect.Transition cycleTransition) {
        currentLightSettings().setCycleTransition(cycleTransition);
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setPlatform(PlatformSettings.Platform platform) {
        platformSettings.setPlatform(platform);
        observers.forEach((observer) -> observer.update(this));
    }

    public void setSyncPlatforms(boolean syncPlatforms) {
        platformSettings.setSyncPlatforms(syncPlatforms);
        observers.forEach((observer) -> observer.update(this));
    }

    /*public void setIp(String ip) {
        this.ip = ip;
        observers.forEach((observer) -> observer.updateAndroidAppConnection(this));
    }

    public void setPort(int port) {
        this.port = port;
        observers.forEach((observer) -> observer.updateAndroidAppConnection(this));
    }*/

}
