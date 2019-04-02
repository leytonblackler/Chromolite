package com.leytonblackler.chromolite.main.settings.categories;

import com.leytonblackler.chromolite.main.settings.presets.DefaultSettings;

public class GeneralSettings {

    private int ledStripLength = DefaultSettings.LED_STRIP_LENGTH;

    /*
    Accessor Methods
     */

    public int getLEDStripLength() {
        return ledStripLength;
    }

    /*
    Mutator Methods
     */

    public void setLEDStripLength(int ledStripLength) {
        this.ledStripLength = ledStripLength;
    }

}
