package com.leytonblackler.chromolite.main.settings.categories;

import com.leytonblackler.chromolite.main.settings.presets.DefaultSettings;

public class PlatformSettings {

    public enum Platform {
        ARDUINO,
        RAZER_CHROMA,
        PHILLIPS_HUE
    }

    private Platform platform = DefaultSettings.PLATFORM;

    private boolean sync = false;

    public Platform getPlatform() {
        return platform;
    }

    public boolean sync() {
        return sync;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }
}
