package com.leytonblackler.chromolite;

import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.model.Model;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.view.GUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Chromolite extends Application {

    /**
     * Singleton instance of the model.
     */
    private static Chromolite instance;

    /**
     * Current user settings configuration.
     */
    private SettingsManager settings;

    /**
     * Core functionality.
     */

    private Model model;

    LEDStripSimulationController ledStripSimulation;

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        settings = new SettingsManager();

        GUI gui = new GUI(stage, settings);
        settings.addObserver(gui.getMainWindow());

        model = new Model(settings, ledStripSimulation);
        settings.addObserver(model);

        //Ensure the scene and model reflect the current settings.
        settings.notifyObservers();
    }

    @Override
    public void stop() {
        model.stop();
    }

    public SettingsManager getSettings() {
        return settings;
    }

    public static Chromolite getInstance() {
        if (instance == null) {
            throw new IllegalStateException();
        }
        return instance;
    }

    public void setLEDStripSimulation(LEDStripSimulationController ledStripSimulation) {
        this.ledStripSimulation = ledStripSimulation;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
