package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.main.settings.Settings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class GeneralSettingsController implements Controller, Initializable {

    @FXML
    private ToggleButton saveButton;

    @FXML
    private ToggleButton loadButton;

    @FXML
    private CheckBox startMinimisedCheckBox;

    @FXML
    private ToggleButton resetButton;

    @FXML
    private ToggleButton razerButton;

    @FXML
    private ToggleButton hueButton;

    @FXML
    public ImageView razerLogo;
    private Image[] razerLogoImages = new Image[2];

    @FXML
    public ImageView hueLogo;
    private Image[] hueLogoImages = new Image[2];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        razerLogoImages[0] = new Image(getClass().getClassLoader().getResource("images/razer_logo_light.png").toExternalForm());
        razerLogoImages[1] = new Image(getClass().getClassLoader().getResource("images/razer_logo_dark.png").toExternalForm());
        razerLogo.setImage(razerLogoImages[0]);

        hueLogoImages[0] = new Image(getClass().getClassLoader().getResource("images/hue_logo_light.png").toExternalForm());
        hueLogoImages[1] = new Image(getClass().getClassLoader().getResource("images/hue_logo_dark.png").toExternalForm());
        hueLogo.setImage(hueLogoImages[0]);
    }

    @Override
    public void update(Settings settings) {
        //
    }

    @FXML
    private void saveButtonClicked() {
        //
    }

    @FXML
    private void loadButtonClicked() {
        //
    }

    @FXML
    private void resetButtonClicked() {
        //
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
}
