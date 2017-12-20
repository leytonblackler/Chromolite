package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.settings.categories.PlatformSettings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PlatformsController  extends Controller implements Initializable {

    @FXML
    private ToggleButton arduinoButton;

    @FXML
    private ToggleButton razerButton;

    @FXML
    private ToggleButton hueButton;

    @FXML
    private CheckBox syncPlatformsCheckBox;

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
        getSettings().setPlatform(PlatformSettings.Platform.ARDUINO);
    }

    @FXML
    private void razerButtonClicked() {
        getSettings().setPlatform(PlatformSettings.Platform.RAZER_CHROMA);
    }

    @FXML
    private void hueButtonClicked() {
        getSettings().setPlatform(PlatformSettings.Platform.PHILLIPS_HUE);
    }

    @FXML
    private void syncPlatformsCheckBoxClicked() {
        getSettings().setSyncPlatforms(syncPlatformsCheckBox.isSelected());
    }

    private void setButtonSelected(boolean selected, ToggleButton button, Image[] images, ImageView buttonImage) {
        button.setSelected(selected);
        buttonImage.setImage(images[selected ? 1 : 0]);
    }

    @Override
    public void update(SettingsManager settings) {
        syncPlatformsCheckBox.setSelected(settings.getSyncPlatforms());
        switch (settings.getPlatform()) {
            case ARDUINO:
                setButtonSelected(true, arduinoButton, arduinoLogoImages, arduinoLogo);
                setButtonSelected(false, razerButton, razerLogoImages, razerLogo);
                setButtonSelected(false, hueButton, hueLogoImages, hueLogo);
                break;
            case RAZER_CHROMA:
                setButtonSelected(false, arduinoButton, arduinoLogoImages, arduinoLogo);
                setButtonSelected(true, razerButton, razerLogoImages, razerLogo);
                setButtonSelected(false, hueButton, hueLogoImages, hueLogo);
                break;
            case PHILLIPS_HUE:
                setButtonSelected(false, arduinoButton, arduinoLogoImages, arduinoLogo);
                setButtonSelected(false, razerButton, razerLogoImages, razerLogo);
                setButtonSelected(true, hueButton, hueLogoImages, hueLogo);
                break;
        }
    }
}
