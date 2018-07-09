package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.settings.categories.PlatformSettings;
import com.leytonblackler.chromolite.view.GUIUtilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
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

    @FXML
    private ChoiceBox platformsChoiceBox;

    @FXML
    private ImageView platformIcon;

    @FXML
    private CheckBox syncPlatformsCheckBox;

    @FXML
    private ToggleButton optionsButton;

    private Image arduinoLogoImage;

    private Image razerLogoImage;

    private Image hueLogoImage;

    private Image nanoleafLogoImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GUIUtilities.initialiseChoiceBox(platformsChoiceBox, PlatformSettings.Platform.values(), PlatformSettings.Platform.ARDUINO);

        arduinoLogoImage = new Image(getClass().getClassLoader().getResource("images/arduino_logo.png").toExternalForm());

        razerLogoImage = new Image(getClass().getClassLoader().getResource("images/razer_logo.png").toExternalForm());

        hueLogoImage = new Image(getClass().getClassLoader().getResource("images/hue_logo.png").toExternalForm());

        nanoleafLogoImage = new Image(getClass().getClassLoader().getResource("images/nanoleaf_logo.png").toExternalForm());
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
        getSettings().setPlatform(PlatformSettings.Platform.valueOf(this.platformsChoiceBox.getValue().toString().replace(' ', '_')));
    }

    @FXML
    private void optionsButtonClicked() {
        //
    }

    private void updateChoiceBoxPadding(PlatformSettings.Platform platform) {
        int left = 0;
        switch (platform) {
            case ARDUINO:
                left = 37;
                break;
            case RAZER_CHROMA:
                left = 22;
                break;
            case PHILIPS_HUE:
                left = 29;
                break;
            case NANOLEAF:
                left = 27;
                break;
        }
        this.platformsChoiceBox.setPadding(new Insets(0, 0, 0, left));
    }

    @FXML
    private void syncPlatformsCheckBoxClicked() {
        getSettings().setSyncPlatforms(syncPlatformsCheckBox.isSelected());
    }

    @Override
    public void update(SettingsManager settings) {
        updateChoiceBoxPadding(settings.getPlatform());
        syncPlatformsCheckBox.setSelected(settings.getSyncPlatforms());
        switch (settings.getPlatform()) {
            case ARDUINO:
                platformIcon.setImage(arduinoLogoImage);
                //setButtonSelected(true, arduinoButton, arduinoLogoImages, arduinoLogo);
                //setButtonSelected(false, razerButton, razerLogoImages, razerLogo);
                //setButtonSelected(false, hueButton, hueLogoImages, hueLogo);
                break;
            case RAZER_CHROMA:
                platformIcon.setImage(razerLogoImage);
                //setButtonSelected(false, arduinoButton, arduinoLogoImages, arduinoLogo);
                //setButtonSelected(true, razerButton, razerLogoImages, razerLogo);
                //setButtonSelected(false, hueButton, hueLogoImages, hueLogo);
                break;
            case PHILIPS_HUE:
                platformIcon.setImage(hueLogoImage);
                //setButtonSelected(false, arduinoButton, arduinoLogoImages, arduinoLogo);
                //setButtonSelected(false, razerButton, razerLogoImages, razerLogo);
                //setButtonSelected(true, hueButton, hueLogoImages, hueLogo);
                break;
            case NANOLEAF:
                platformIcon.setImage(nanoleafLogoImage);
                break;
        }
    }
}
