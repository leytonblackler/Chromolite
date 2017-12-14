package com.leytonblackler.chromolite.main.settings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class SettingsObserver {

    public void update(SettingsManager settings) {
        for (Method method : this.getClass().getDeclaredMethods()) {
            if (method.getName().contains("update") && !method.getName().equals("update")) {
                try {
                    method.invoke(this, settings);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public abstract void updateSpectrum(SettingsManager settings);

    public abstract void updateColours(SettingsManager settings);

    public abstract void updateModes(SettingsManager settings);

    public abstract void updateModeSettings(SettingsManager settings);

    public abstract void updatePlatformSettings(SettingsManager settings);

    public abstract void updateGeneralSettings(SettingsManager settings);

    public abstract void updateAndroidAppConnection(SettingsManager settings);

}