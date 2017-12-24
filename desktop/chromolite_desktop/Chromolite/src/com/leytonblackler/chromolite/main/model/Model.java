package com.leytonblackler.chromolite.main.model;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.effecthandler.EffectThread;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.ArduinoEffectPlatform;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.PhillipsHueEffectPlatform;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.RazerChromaEffectPlatform;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.settings.SettingsObserver;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;
import com.leytonblackler.chromolite.main.settings.categories.PlatformSettings;
import com.leytonblackler.chromolite.main.settings.presets.DefaultSettings;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

import java.util.*;

public class Model extends SettingsObserver {

    private ArduinoController arduinoController;

    private RazerChromaService razerChromaService;

    private EffectThread allPlatformsThread;

    private EffectThread arduinoPlatformThread;

    private EffectThread razerChromaPlatformThread;

    private EffectThread phillipsHuePlatformThread;

    //TEMPORARY
    public static LEDStripSimulationController ledStripSim;

    public Model(SettingsManager settings, LEDStripSimulationController ledStripSimulation) {
        //TEMPORARY
        ledStripSim = ledStripSimulation;

        arduinoController = new ArduinoController();
        razerChromaService = new RazerChromaService();
        razerChromaService.start();

        EffectPlatform arduinoEffectPlatform = new ArduinoEffectPlatform(arduinoController);
        EffectPlatform razerChromaEffectPlatform = new RazerChromaEffectPlatform(razerChromaService);
        EffectPlatform phillipsHueEffectPlatform = new PhillipsHueEffectPlatform();

        allPlatformsThread = new EffectThread(arduinoEffectPlatform, razerChromaEffectPlatform, phillipsHueEffectPlatform);
        arduinoPlatformThread = new EffectThread(arduinoEffectPlatform);
        razerChromaPlatformThread = new EffectThread(razerChromaEffectPlatform);
        phillipsHuePlatformThread = new EffectThread(phillipsHueEffectPlatform);
    }

    public void stop() {
        allPlatformsThread.stop();
        arduinoPlatformThread.stop();
        razerChromaPlatformThread.stop();
        phillipsHuePlatformThread.stop();

        razerChromaService.stop();
        //arduinoController.stop();
        //phillipsHueController.stop();
    }

    @Override
    public void updateSpectrum(SettingsManager settings) {

    }

    @Override
    public void updateColours(SettingsManager settings) {

    }

    @Override
    public void updateModes(SettingsManager settings) {
        if (allPlatformsThread.getCurrentMode() != settings.getMode()) {
            allPlatformsThread.setEffect(settings.getCurrentLightSettings());
        }
        if (arduinoPlatformThread.getCurrentMode() != settings.getArduinoSettings().getMode()) {
            arduinoPlatformThread.setEffect(settings.getArduinoSettings());
        }
        if (razerChromaPlatformThread.getCurrentMode() != settings.getRazerChromaSettings().getMode()) {
            razerChromaPlatformThread.setEffect(settings.getRazerChromaSettings());
        }
        if (phillipsHuePlatformThread.getCurrentMode() != settings.getPhillipsHueSettings().getMode()) {
            phillipsHuePlatformThread.setEffect(settings.getPhillipsHueSettings());
        }
    }

    @Override
    public void updateModeSettings(SettingsManager settings) {

    }

    @Override
    public void updatePlatformSettings(SettingsManager settings) {
        if (settings.getSyncPlatforms()) {
            allPlatformsThread.setEffect(settings.getCurrentLightSettings());
            if (!allPlatformsThread.isRunning()) {
                arduinoPlatformThread.stop();
                razerChromaPlatformThread.stop();
                phillipsHuePlatformThread.stop();
                allPlatformsThread.start();
            }
        }

        if (!settings.getSyncPlatforms() && !arduinoPlatformThread.isRunning() && !razerChromaPlatformThread.isRunning() && !phillipsHuePlatformThread.isRunning()) {
            allPlatformsThread.stop();
            arduinoPlatformThread.start();
            razerChromaPlatformThread.start();
            phillipsHuePlatformThread.start();
        }
    }

    @Override
    public void updateGeneralSettings(SettingsManager settings) {

    }

    @Override
    public void updateAndroidAppConnection(SettingsManager settings) {

    }
}
