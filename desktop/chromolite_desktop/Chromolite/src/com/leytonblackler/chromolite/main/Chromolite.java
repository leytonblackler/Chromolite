package com.leytonblackler.chromolite.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Chromolite extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/View.fxml"));

        Scene scene = new Scene(root, 560, 750);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("view/Style.css").toExternalForm());

        primaryStage.setTitle("Chromolite");
        primaryStage.setScene(scene);
        primaryStage.show();
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
