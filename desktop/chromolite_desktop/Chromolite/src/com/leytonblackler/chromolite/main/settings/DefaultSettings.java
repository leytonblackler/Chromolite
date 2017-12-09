package com.leytonblackler.chromolite.main.settings;

import com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect;

public final class DefaultSettings {

    public static final int LED_STRIP_LENGTH = 59;

    public static final Settings.ColourSelector COLOUR_SELECTOR = Settings.ColourSelector.PRIMARY;

    public static final int[] PRIMARY_COLOUR = { 255, 0, 0 };
    public static final int[] SECONDARY_COLOUR = { 0, 255, 0 };
    public static final int[] TERTIARY_COLOUR = { 0, 0, 255 };

    public static final Settings.Mode MODE = Settings.Mode.STATIC;

    public static final int BRIGHTNESS = 100;
    public static final int SPEED = 50;

    public static final CycleEffect.NumberOfColours CYCLE_NUMBER_OF_COLOURS = CycleEffect.NumberOfColours.SPECTRUM;

    public static final boolean SYNC_WITH_RAZER = true;

    public static final boolean RAZER_CHROMA_ENABLED = true;

    public static final String IP = "192.168.0.0";
    public static final int PORT = 5000;
}
