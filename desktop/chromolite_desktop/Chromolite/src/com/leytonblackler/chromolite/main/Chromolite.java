package com.leytonblackler.chromolite.main;

import com.leytonblackler.chromolite.main.effects.EffectHandler;
import com.leytonblackler.chromolite.main.effects.RandomEffect;
import com.leytonblackler.chromolite.main.modes.Mode;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.utilities.LEDController;
import com.leytonblackler.chromolite.main.utilities.razerchroma.RazerChromaService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Chromolite extends Application {

    /**
     * Singleton instance of the model.
     */
    private static Chromolite model;

    /**
     * Current user settings configuration.
     */
    private Settings settings;

    /**
     * Handles the current effect.
     */
    private EffectHandler effectHandler;

    private LEDController ledController;

    private RazerChromaService razerChromaService;

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = this;
        settings = new Settings();
        razerChromaService = new RazerChromaService();
        ledController = new LEDController();

        effectHandler = new EffectHandler();
        effectHandler.setEffect(new RandomEffect());
        effectHandler.run();

        //Create the scene (window contents) from the main FXML file.
        Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("view/View.fxml")));
        //Apply the CSS styling to the scene (window contents).
        scene.getStylesheets().add(getClass().getClassLoader().getResource("view/Style.css").toExternalForm());
        //Initialise the stage (window).
        primaryStage.setTitle("Chromolite");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        //Display the stage (window).
        primaryStage.show();
    }

    @Override
    public void stop() {
        //Stop running the effect handler.
        effectHandler.stop();
        //Stop running the Razer Chroma SDK Service.
        razerChromaService.stop();
    }

    public Settings getSettings() {
        return settings;
    }

    public RazerChromaService getRazerChromaService() {
        return razerChromaService;
    }

    public LEDController getLEDController() {
        return ledController;
    }

    public static Chromolite getInstance() {
        if (model == null) {
            throw new IllegalStateException();
        }
        return model;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
