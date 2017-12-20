package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.settings.categories.GeneralSettings;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;
import com.leytonblackler.chromolite.main.settings.presets.DefaultSettings;
import com.leytonblackler.chromolite.view.Constants;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Cell;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class LEDStripSimulationController extends Controller implements Initializable {

    private static final int LEDS = DefaultSettings.LED_STRIP_LENGTH;

    @FXML
    private GridPane ledStrip;

    @Override
    public void update(SettingsManager settings) {
        //
    }

    /*public void setLEDStripWidth() {
        ledStrip.getChildren().clear();
        double ledWidth = ((windowWidth - (2 * PADDING)) - ((PADDING / 4) * (LEDS))) / LEDS;
        System.out.println(windowWidth + " " + ledWidth);
        for (int i = 0; i < LEDS; i++) {
            Region led = new Region();
            led.setPrefWidth(Math.round(ledWidth));
            led.setStyle("-fx-background-color: red;" +
                    "-fx-background-radius: 3 3 3 3;");
            ledStrip.getChildren().add(led);
        }
    }*/

    public void setLED(int index, int r, int g, int b) {
        ledStrip.getChildren().get(index).setStyle("-fx-background-color: rgb(" + r + "," + g + "," + b + ");" +
                "-fx-background-radius: 3 3 3 3;");
    }

    public void setAll(int r, int g, int b) {
        for (int i = 0; i < LEDS; i++) {
            setLED(i, r, g, b);
        }
    }

    public void setLayout(int[][] layout) {
        //The colour layout must be the correct format for the led strip.
        if (layout.length != LEDS || layout[0].length != 3) {
            throw new IllegalArgumentException("The layout format is invalid.");
        }
        for (int i = 0; i < layout.length; i++) {
            setLED(i, layout[i][0], layout[i][1], layout[i][2]);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ledStrip.getChildren().clear();
        for (int i = 0; i < LEDS; i++) {
            Region led = new Region();
            GridPane.setColumnIndex(led, i);
            GridPane.setHgrow(led, Priority.ALWAYS);
            led.setPrefHeight(Constants.PADDING.getValue() / 3);
            led.setStyle("-fx-background-color: red;" +
                    "-fx-background-radius: 3 3 3 3;");
            ledStrip.getChildren().add(led);
        }
    }
}
