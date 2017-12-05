package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.main.settings.Settings;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Cell;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.ResourceBundle;

public class LEDStripSimulationController implements Controller {

    private static final int LEDS = 30;

    @FXML
    private Double PADDING;

    @FXML
    private HBox ledStrip;

    @Override
    public void update(Settings settings) {
        //
    }

    public void setLEDStripWidth(double windowWidth) {
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
    }
}
