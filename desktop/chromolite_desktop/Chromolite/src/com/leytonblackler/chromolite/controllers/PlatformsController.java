package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.settings.categories.PlatformSettings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class PlatformsController  extends Controller implements Initializable {

    private static final String[] PLATFORMS = { "ARDUINO", "RAZER CHROMA", "PHILIPS HUE", "NANOLEAF" };

    @FXML
    private ComboBox platformComboBox;

    @FXML
    private ImageView platformIcon;

    @FXML
    private CheckBox syncPlatformsCheckBox;

    @FXML
    private ToggleButton optionsButton;

    private Image[] arduinoLogoImages = new Image[2];

    private Image[] razerLogoImages = new Image[2];

    private Image[] hueLogoImages = new Image[2];

    private Image[] nanoleafLogoImages = new Image[2];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        arduinoLogoImages[0] = new Image(getClass().getClassLoader().getResource("images/arduino_logo_light.png").toExternalForm());
        arduinoLogoImages[1] = new Image(getClass().getClassLoader().getResource("images/arduino_logo_dark.png").toExternalForm());

        razerLogoImages[0] = new Image(getClass().getClassLoader().getResource("images/razer_logo_light.png").toExternalForm());
        razerLogoImages[1] = new Image(getClass().getClassLoader().getResource("images/razer_logo_dark.png").toExternalForm());

        hueLogoImages[0] = new Image(getClass().getClassLoader().getResource("images/hue_logo_light.png").toExternalForm());
        hueLogoImages[1] = new Image(getClass().getClassLoader().getResource("images/hue_logo_dark.png").toExternalForm());

        nanoleafLogoImages[0] = new Image(getClass().getClassLoader().getResource("images/nanoleaf_logo_light.png").toExternalForm());
        nanoleafLogoImages[1] = new Image(getClass().getClassLoader().getResource("images/nanoleaf_logo_dark.png").toExternalForm());

        platformIcon.setImage(arduinoLogoImages[0]);

        /*platformComboBox.setItems(FXCollections.observableArrayList(PLATFORMS));
        platformComboBox.setCellFactory(param -> new PlatformListCell());
        platformComboBox.setButtonCell(new PlatformListCell());
        platformComboBox.setValue(PLATFORMS[0]);*/
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
        getSettings().setPlatform(PlatformSettings.Platform.PHILIPS_HUE);
    }

    @FXML
    private void platformChoiceBoxChanged() {
        //
    }

    @FXML
    private void optionsButtonClicked() {
        //
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
                //setButtonSelected(true, arduinoButton, arduinoLogoImages, arduinoLogo);
                //setButtonSelected(false, razerButton, razerLogoImages, razerLogo);
                //setButtonSelected(false, hueButton, hueLogoImages, hueLogo);
                break;
            case RAZER_CHROMA:
                //setButtonSelected(false, arduinoButton, arduinoLogoImages, arduinoLogo);
                //setButtonSelected(true, razerButton, razerLogoImages, razerLogo);
                //setButtonSelected(false, hueButton, hueLogoImages, hueLogo);
                break;
            case PHILIPS_HUE:
                //setButtonSelected(false, arduinoButton, arduinoLogoImages, arduinoLogo);
                //setButtonSelected(false, razerButton, razerLogoImages, razerLogo);
                //setButtonSelected(true, hueButton, hueLogoImages, hueLogo);
                break;
        }
    }
}
