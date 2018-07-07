package com.leytonblackler.chromolite.main.settings.categories;

import com.leytonblackler.chromolite.main.settings.presets.DefaultSettings;

public class PlatformSettings {

    public enum Platform {
        ARDUINO("ARDUINO"),
        RAZER_CHROMA("RAZER CHROMA"),
        PHILIPS_HUE("PHILIPS HUE"),
        NANOLEAF("NANOLEAF");

        private String label;

        Platform(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return this.label;
        }
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
