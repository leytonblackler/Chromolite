package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.main.settings.SettingsManager;

public abstract class Controller {

    private static SettingsManager settings;

    public abstract void update(SettingsManager settings);

    public static void setSettings(SettingsManager settings) {
        Controller.settings = settings;
    }

    protected SettingsManager getSettings() {
        return settings;
    }

}
