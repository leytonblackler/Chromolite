package com.leytonblackler.chromolite.main.settings.presets;

import com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect;
import com.leytonblackler.chromolite.main.effecthandler.effects.StaticEffect;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;
import com.leytonblackler.chromolite.main.settings.categories.PlatformSettings;

import java.util.Vector;

public final class DefaultSettings {

    public static final int LED_STRIP_LENGTH = 60;

    public static final LightSettings.ColourSelector COLOUR_SELECTOR = LightSettings.ColourSelector.PRIMARY;

    public static final int[] PRIMARY_COLOUR = { 255, 0, 0 };
    public static final int[] SECONDARY_COLOUR = { 0, 255, 0 };
    public static final int[] TERTIARY_COLOUR = { 0, 0, 255 };

    public static final int[] PRIMARY_INDICATOR_POSITION = { 100, 125 };
    public static final int[] SECONDARY_INDICATOR_POSITION = { 300, 125 };
    public static final int[] TERTIARY_INDICATOR_POSITION = { 500, 125 };

    public static final LightSettings.Mode MODE = LightSettings.Mode.STATIC;

    public static final int BRIGHTNESS = 100;
    public static final int SPEED = 50;

    public static final StaticEffect.Style STATIC_STYLE = StaticEffect.Style.SOLID;
    public static final StaticEffect.NumberOfColours STATIC_NUMBER_OF_COLOURS = StaticEffect.NumberOfColours.ONE;

    public static final CycleEffect.NumberOfColours CYCLE_NUMBER_OF_COLOURS = CycleEffect.NumberOfColours.SPECTRUM;
    public static final CycleEffect.Transition CYCLE_TRANSITION = CycleEffect.Transition.BLEND;

    public static final PlatformSettings.Platform PLATFORM = PlatformSettings.Platform.ARDUINO;
    public static final boolean SYNC_PLATFORMS = true;

    public static final String IP = "192.168.0.0";
    public static final int PORT = 5000;
}
