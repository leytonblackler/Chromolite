package com.leytonblackler.chromolite.main.settings.categories;

import com.leytonblackler.chromolite.main.settings.presets.DefaultSettings;

public class PlatformSettings {

    public enum Platform {
        ARDUINO,
        RAZER_CHROMA,
        PHILIPS_HUE
    }

    private Platform platform = DefaultSettings.PLATFORM;

    private boolean syncPlatforms = DefaultSettings.SYNC_PLATFORMS;

    public Platform getPlatform() {
        return platform;
    }

    public boolean getSyncPlatforms() {
        return syncPlatforms;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public void setSyncPlatforms(boolean syncPlatforms) {
        this.syncPlatforms = syncPlatforms;
    }
}
