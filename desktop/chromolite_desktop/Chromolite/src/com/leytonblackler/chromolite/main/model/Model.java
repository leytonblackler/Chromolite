package com.leytonblackler.chromolite.main.model;

import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.effecthandler.EffectThread;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.ArduinoEffectPlatform;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.EffectPlatform;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.PhilipsHueEffectPlatform;
import com.leytonblackler.chromolite.main.effecthandler.effectplatforms.RazerChromaEffectPlatform;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.settings.SettingsObserver;
import com.leytonblackler.chromolite.main.utilities.arduino.ArduinoController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;

public class Model extends SettingsObserver {

    private ArduinoController arduinoController;

    private RazerChromaService razerChromaService;

    private EffectThread allPlatformsThread;

    private EffectThread arduinoPlatformThread;

    private EffectThread razerChromaPlatformThread;

    private EffectThread philipsHuePlatformThread;

    //TEMPORARY
    public static LEDStripSimulationController ledStripSim;

    public Model(SettingsManager settings, LEDStripSimulationController ledStripSimulation) {
        //TEMPORARY
        ledStripSim = ledStripSimulation;

        arduinoController = new ArduinoController();
        razerChromaService = new RazerChromaService();
        razerChromaService.start();

        arduinoController.connect();

        EffectPlatform arduinoEffectPlatform = new ArduinoEffectPlatform(arduinoController);
        EffectPlatform razerChromaEffectPlatform = new RazerChromaEffectPlatform(razerChromaService);
        EffectPlatform philipsHueEffectPlatform = new PhilipsHueEffectPlatform();

        allPlatformsThread = new EffectThread(arduinoEffectPlatform, razerChromaEffectPlatform, philipsHueEffectPlatform);
        arduinoPlatformThread = new EffectThread(arduinoEffectPlatform);
        razerChromaPlatformThread = new EffectThread(razerChromaEffectPlatform);
        philipsHuePlatformThread = new EffectThread(philipsHueEffectPlatform);
    }

    public void stop() {
        allPlatformsThread.stop();
        arduinoPlatformThread.stop();
        razerChromaPlatformThread.stop();
        philipsHuePlatformThread.stop();

        razerChromaService.stop();
        //arduinoController.stop();
        //philipsHueController.stop();
    }

    @Override
    public void update(SettingsManager settings) {
        updateModes(settings);
        updatePlatformSettings(settings);
    }

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
        if (philipsHuePlatformThread.getCurrentMode() != settings.getPhilipsHueSettings().getMode()) {
            philipsHuePlatformThread.setEffect(settings.getPhilipsHueSettings());
        }
    }
    public void updatePlatformSettings(SettingsManager settings) {
        if (settings.getSyncPlatforms()) {
            allPlatformsThread.setEffect(settings.getCurrentLightSettings());
            if (!allPlatformsThread.isRunning()) {
                arduinoPlatformThread.stop();
                razerChromaPlatformThread.stop();
                philipsHuePlatformThread.stop();
                allPlatformsThread.start();
            }
        }

        if (!settings.getSyncPlatforms() && !arduinoPlatformThread.isRunning() && !razerChromaPlatformThread.isRunning() && !philipsHuePlatformThread.isRunning()) {
            allPlatformsThread.stop();
            arduinoPlatformThread.start();
            razerChromaPlatformThread.start();
            philipsHuePlatformThread.start();
        }
    }
}
