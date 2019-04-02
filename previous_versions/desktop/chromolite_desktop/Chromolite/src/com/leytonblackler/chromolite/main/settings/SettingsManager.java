package com.leytonblackler.chromolite.main.settings;

import com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect;
import com.leytonblackler.chromolite.main.effecthandler.effects.ScanEffect;
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

    private LightSettings philipsHueSettings;

    private PlatformSettings platformSettings;

    private GeneralSettings generalSettings;

    private AppConnectSettings appConnectSettings;

    public SettingsManager() {
        arduinoSettings = new LightSettings();
        razerChromaSettings = new LightSettings();
        philipsHueSettings = new LightSettings();
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
            case PHILIPS_HUE:
                return philipsHueSettings;
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

    public LightSettings getPhilipsHueSettings() {
        return philipsHueSettings;
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
        notifyObservers();
    }

    public void setPrimaryColour(int[] primaryColour) {
        getCurrentLightSettings().setPrimaryColour(primaryColour);
        notifyObservers();
    }

    public void setSecondaryColour(int[] secondaryColour) {
        getCurrentLightSettings().setSecondaryColour(secondaryColour);
        notifyObservers();
    }

    public void setTertiaryColour(int[] tertiaryColour) {
        getCurrentLightSettings().setTertiaryColour(tertiaryColour);
        notifyObservers();
    }

    public void setPrimaryIndicatorPosition(int x, int y) {
        getCurrentLightSettings().setPrimaryIndicatorPosition(x, y);
        notifyObservers();
    }

    public void setSecondaryIndicatorPosition(int x, int y) {
        getCurrentLightSettings().setSecondaryIndicatorPosition(x, y);
        notifyObservers();
    }

    public void setTertiaryIndicatorPosition(int x, int y) {
        getCurrentLightSettings().setTertiaryIndicatorPosition(x, y);
        notifyObservers();
    }

    public void setMode(LightSettings.Mode mode) {
        getCurrentLightSettings().setMode(mode);
        notifyObservers();
    }

    public void setBrightness(int brightness) {
        getCurrentLightSettings().setBrightness(brightness);
        notifyObservers();
    }

    public void setSpeed(int speed) {
        getCurrentLightSettings().setSpeed(speed);
        notifyObservers();
    }

    public void setStaticStyle(StaticEffect.Style staticStyle) {
        getCurrentLightSettings().setStaticStyle(staticStyle);
        notifyObservers();
    }

    public void setStaticNumberOfColours(StaticEffect.NumberOfColours staticNumberOfColours) {
        getCurrentLightSettings().setStaticNumberOfColours(staticNumberOfColours);
        notifyObservers();
    }

    public void setCycleNumberOfColours(CycleEffect.NumberOfColours cycleNumberOfColours) {
        getCurrentLightSettings().setCycleNumberOfColours(cycleNumberOfColours);
        notifyObservers();
    }

    public void setCycleTransition(CycleEffect.Transition cycleTransition) {
        getCurrentLightSettings().setCycleTransition(cycleTransition);
        notifyObservers();
    }

    public void setWaveNumberOfColours(WaveEffect.NumberOfColours waveNumberOfColours) {
        getCurrentLightSettings().setWaveNumberOfColours(waveNumberOfColours);
        notifyObservers();
    }

    public void setWaveDirection(WaveEffect.Direction waveDirection) {
        getCurrentLightSettings().setWaveDirection(waveDirection);
        notifyObservers();
    }

    public void setPlatform(PlatformSettings.Platform platform) {
        platformSettings.setPlatform(platform);
        notifyObservers();
    }

    public void setSyncPlatforms(boolean syncPlatforms) {
        platformSettings.setSyncPlatforms(syncPlatforms);
        notifyObservers();
    }

    public void setScanSize(ScanEffect.Size scanSize) {
        getCurrentLightSettings().setScanSize(scanSize);
        notifyObservers();
    }

    public void setScanBackground(boolean scanBackground) {
        getCurrentLightSettings().setScanBackground(scanBackground);
        notifyObservers();
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
