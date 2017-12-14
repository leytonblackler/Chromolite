package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.main.settings.SettingsManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PlatformsController implements Controller, Initializable {

    @FXML
    private ToggleButton arduinoButton;

    @FXML
    private ToggleButton razerButton;

    @FXML
    private ToggleButton hueButton;

    @FXML
    public ImageView arduinoLogo;
    private Image[] arduinoLogoImages = new Image[2];

    @FXML
    public ImageView razerLogo;
    private Image[] razerLogoImages = new Image[2];

    @FXML
    public ImageView hueLogo;
    private Image[] hueLogoImages = new Image[2];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arduinoLogoImages[0] = new Image(getClass().getClassLoader().getResource("images/arduino_logo_light.png").toExternalForm());
        arduinoLogoImages[1] = new Image(getClass().getClassLoader().getResource("images/arduino_logo_dark.png").toExternalForm());
        arduinoLogo.setImage(arduinoLogoImages[0]);

        razerLogoImages[0] = new Image(getClass().getClassLoader().getResource("images/razer_logo_light.png").toExternalForm());
        razerLogoImages[1] = new Image(getClass().getClassLoader().getResource("images/razer_logo_dark.png").toExternalForm());
        razerLogo.setImage(razerLogoImages[0]);

        hueLogoImages[0] = new Image(getClass().getClassLoader().getResource("images/hue_logo_light.png").toExternalForm());
        hueLogoImages[1] = new Image(getClass().getClassLoader().getResource("images/hue_logo_dark.png").toExternalForm());
        hueLogo.setImage(hueLogoImages[0]);
    }

    @FXML
    private void arduinoButtonClicked() {
        System.out.println("razer");
        if (arduinoButton.selectedProperty().get()) {
            arduinoLogo.setImage(arduinoLogoImages[1]);
        } else {
            arduinoLogo.setImage(arduinoLogoImages[0]);
        }
    }

    @FXML
    private void razerButtonClicked() {
        System.out.println("razer");
        if (razerButton.selectedProperty().get()) {
            razerLogo.setImage(razerLogoImages[1]);
        } else {
            razerLogo.setImage(razerLogoImages[0]);
        }
    }

    @FXML
    private void hueButtonClicked() {
        System.out.println("hue");
        if (hueButton.selectedProperty().get()) {
            hueLogo.setImage(hueLogoImages[1]);
        } else {
            hueLogo.setImage(hueLogoImages[0]);
        }
    }

    @Override
    public void update(SettingsManager settings) {
        //
    }
}
