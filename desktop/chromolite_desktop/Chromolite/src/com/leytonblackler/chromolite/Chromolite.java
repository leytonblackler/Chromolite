package com.leytonblackler.chromolite;

import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.model.Model;
import com.leytonblackler.chromolite.main.settings.Settings;
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
    private Settings settings;

    /**
     * Core functionality.
     */

    private Model model;

    LEDStripSimulationController ledStripSimulation;

    @Override
    public void start(Stage primaryStage) throws Exception {
        instance = this;
        settings = new Settings();

        //Create the scene (window contents) from the main FXML file.
        Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("view/View.fxml")));
        //Apply the CSS styling to the scene (window contents).
        scene.getStylesheets().add(getClass().getClassLoader().getResource("view/Style.css").toExternalForm());
        //Initialise the stage (window).
        primaryStage.setTitle("Chromolite");
        primaryStage.setWidth(1200);
        //primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();

        //Display the stage (window).
        primaryStage.show();
        System.out.println("show");

        model = new Model(ledStripSimulation);

        //Ensure the scene and model reflect the current settings.
        settings.notifyObservers();
    }

    @Override
    public void stop() {
        model.stop();
    }

    public Settings getSettings() {
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
