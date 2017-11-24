package com.leytonblackler.chromolite.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Chromolite extends Application {

    /**
     * Singleton instance of the model.
     */
    private static Chromolite model;

    @Override
    public void start(Stage primaryStage) throws Exception {
        model = this;
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

    public static Chromolite getInstance() {
        if (model == null) {
            throw new IllegalStateException();
        }
        return model;
    }

    public static void main(String[] args) {
        launch(args);

        /**
        //Create a new instance of the Razer Chroma SDK Service.
        RazerChromaService razerChromaService = new RazerChromaService();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Stop running the Razer Chroma SDK Service.
        razerChromaService.stop();**/
    }
}
