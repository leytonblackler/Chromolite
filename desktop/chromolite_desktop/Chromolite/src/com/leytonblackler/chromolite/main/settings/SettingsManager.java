package com.leytonblackler.chromolite.main.settings;

import com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect;
import com.leytonblackler.chromolite.main.effecthandler.effects.StaticEffect;
import com.leytonblackler.chromolite.main.effecthandler.effects.WaveEffect;
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

    public LightSettings getCurrentLightSettings() {
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
        return getCurrentLightSettings().getColourSelector();
    }

    public int[] getPrimaryColour() {
        return getCurrentLightSettings().getPrimaryColour();
    }

    public int[] getSecondaryColour() {
        return getCurrentLightSettings().getSecondaryColour();
    }

    public int[] getTertiaryColour() {
        return getCurrentLightSettings().getTertiaryColour();
    }

    public int[] getPrimaryIndicatorPosition() {
        return getCurrentLightSettings().getPrimaryIndicatorPosition();
    }

    public int[] getSecondaryIndicatorPosition() {
        return getCurrentLightSettings().getSecondaryIndicatorPosition();
    }

    public int[] getTertiaryIndicatorPosition() {
        return getCurrentLightSettings().getTertiaryIndicatorPosition();
    }

    public LightSettings.Mode getMode() {
        return getCurrentLightSettings().getMode();
    }

    public int getBrightness() {
        return getCurrentLightSettings().getBrightness();
    }

    public int getSpeed() {
        return getCurrentLightSettings().getSpeed();
    }

    public StaticEffect.Style getStaticStyle() {
        return getCurrentLightSettings().getStaticStyle();
    }
    public StaticEffect.NumberOfColours getStaticNumberOfColours() {
        return getCurrentLightSettings().getStaticNumberOfColours();
    }

    public CycleEffect.NumberOfColours getCycleNumberOfColours() {
        return getCurrentLightSettings().getCycleNumberOfColours();
    }
    public CycleEffect.Transition getCycleTransition() {
        return getCurrentLightSettings().getCycleTransition();
    }

    public WaveEffect.NumberOfColours getWaveNumberOfColours() {
        return getCurrentLightSettings().getWaveNumberOfColours();
    }
    public WaveEffect.Direction getWaveDirection() {
        return getCurrentLightSettings().getWaveDirection();
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
        getCurrentLightSettings().setColourSelector(colourSelector);
        observers.forEach((observer) -> observer.updateColours(this));
    }

    public void setPrimaryColour(int[] primaryColour) {
        getCurrentLightSettings().setPrimaryColour(primaryColour);
        observers.forEach((observer) -> observer.updateColours(this));
        observers.forEach((observer) -> observer.updateSpectrum(this));
    }

    public void setSecondaryColour(int[] secondaryColour) {
        getCurrentLightSettings().setSecondaryColour(secondaryColour);
        observers.forEach((observer) -> observer.updateColours(this));
        observers.forEach((observer) -> observer.updateSpectrum(this));
    }

    public void setTertiaryColour(int[] tertiaryColour) {
        getCurrentLightSettings().setTertiaryColour(tertiaryColour);
        observers.forEach((observer) -> observer.updateColours(this));
        observers.forEach((observer) -> observer.updateSpectrum(this));
    }

    public void setPrimaryIndicatorPosition(int x, int y) {
        getCurrentLightSettings().setPrimaryIndicatorPosition(x, y);
        observers.forEach((observer) -> observer.updateSpectrum(this));
    }

    public void setSecondaryIndicatorPosition(int x, int y) {
        getCurrentLightSettings().setSecondaryIndicatorPosition(x, y);
        observers.forEach((observer) -> observer.updateSpectrum(this));
    }

    public void setTertiaryIndicatorPosition(int x, int y) {
        getCurrentLightSettings().setTertiaryIndicatorPosition(x, y);
        observers.forEach((observer) -> observer.updateSpectrum(this));
    }

    public void setMode(LightSettings.Mode mode) {
        getCurrentLightSettings().setMode(mode);
        observers.forEach((observer) -> observer.updateModes(this));
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setBrightness(int brightness) {
        getCurrentLightSettings().setBrightness(brightness);
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setSpeed(int speed) {
        getCurrentLightSettings().setSpeed(speed);
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setStaticStyle(StaticEffect.Style staticStyle) {
        getCurrentLightSettings().setStaticStyle(staticStyle);
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setStaticNumberOfColours(StaticEffect.NumberOfColours staticNumberOfColours) {
        getCurrentLightSettings().setStaticNumberOfColours(staticNumberOfColours);
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setCycleNumberOfColours(CycleEffect.NumberOfColours cycleNumberOfColours) {
        getCurrentLightSettings().setCycleNumberOfColours(cycleNumberOfColours);
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setCycleTransition(CycleEffect.Transition cycleTransition) {
        getCurrentLightSettings().setCycleTransition(cycleTransition);
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setWaveNumberOfColours(WaveEffect.NumberOfColours waveNumberOfColours) {
        getCurrentLightSettings().setWaveNumberOfColours(waveNumberOfColours);
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setWaveDirection(WaveEffect.Direction waveDirection) {
        getCurrentLightSettings().setWaveDirection(waveDirection);
        observers.forEach((observer) -> observer.updateModeSettings(this));
    }

    public void setPlatform(PlatformSettings.Platform platform) {
        platformSettings.setPlatform(platform);
        observers.forEach((observer) -> observer.update(this));
    }

    public void setSyncPlatforms(boolean syncPlatforms) {
        platformSettings.setSyncPlatforms(syncPlatforms);
        observers.forEach((observer) -> observer.updateModes(this));
        observers.forEach((observer) -> observer.updatePlatformSettings(this));
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
