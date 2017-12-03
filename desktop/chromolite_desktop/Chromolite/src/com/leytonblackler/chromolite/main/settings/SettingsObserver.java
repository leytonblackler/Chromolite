package com.leytonblackler.chromolite.main.settings;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class SettingsObserver {

    public void update(Settings settings) {
        for (Method method : getClass().getDeclaredMethods()) {
            if (method.getName().equals("update")) {
                continue;
            }
            try {
                method.invoke(settings);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void updateSpectrum(Settings settings);

    public abstract void updateColours(Settings settings);

    public abstract void updateModes(Settings settings);

    public abstract void updateModeSettings(Settings settings);

    public abstract void updateGeneralOptions(Settings settings);

    public abstract void updateAndroidAppConnection(Settings settings);

}