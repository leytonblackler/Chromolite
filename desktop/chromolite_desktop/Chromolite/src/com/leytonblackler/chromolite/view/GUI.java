package com.leytonblackler.chromolite.view;

import com.leytonblackler.chromolite.controllers.Controller;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.view.windows.MainWindow;
import com.leytonblackler.chromolite.view.windows.Window;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GUI {

    public static double SCALE = 1;

    private Window mainWindow;

    public GUI(Stage stage, SettingsManager settings) {
        //Set the settings instance the controllers modify.
        Controller.setSettings(settings);
        //Enhances the smoothness of text rendering.
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");
        //Load the Roboto Bold font.
        String fontPath = getClass().getClassLoader().getResource("fonts/Roboto-Bold.ttf").toExternalForm();
        fontPath = fontPath.replaceAll("%20", " ");
        Font.loadFont(fontPath, 10);

        mainWindow = new MainWindow(stage);
    }

    public Window getMainWindow() {
        return mainWindow;
    }
}
