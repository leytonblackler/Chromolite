package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;
import com.leytonblackler.chromolite.view.Constants;
import com.leytonblackler.chromolite.view.MutableDouble;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class ModeController implements Controller, Initializable {

    @FXML
    private ToggleButton staticButton;

    @FXML
    private ToggleButton cycleButton;

    @FXML
    private ToggleButton waveButton;

    @FXML
    private ToggleButton musicButton;

    @FXML
    private ToggleButton scanButton;

    @FXML
    private ToggleButton strobeButton;

    @FXML
    private ToggleButton offButton;

    @FXML
    private ToggleButton disableButton;

    private Set<ToggleButton> buttons;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new HashSet<>();
        buttons.add(staticButton);
        buttons.add(cycleButton);
        buttons.add(waveButton);
        buttons.add(musicButton);
        buttons.add(scanButton);
        buttons.add(strobeButton);
        buttons.add(offButton);
        buttons.add(disableButton);
        buttons.forEach((button) -> button.prefHeightProperty().bind(Constants.BUTTON_HEIGHT));
        //buttonGrid.hgapProperty().bind(Constants.PADDING);
        //buttonGrid.vgapProperty().bind(Constants.PADDING);
    }

    @Override
    public void update(SettingsManager settings) {
        buttons.forEach((button) -> setButtonState(button, settings.getMode()));
    }

    /**
     * Sets the selected state of the button based on the given mode.
     * @param button The button to modify the state of.
     * @param mode The mode to determine the state from.
     */
    private void setButtonState(ToggleButton button, LightSettings.Mode mode) {
        button.setSelected(button.getText().equals(mode.toString()));
    }

    @FXML
    public void staticButtonClicked() {
        setMode(LightSettings.Mode.STATIC);
    }

    @FXML
    public void cycleButtonClicked() {
        setMode(LightSettings.Mode.CYCLE);
    }

    @FXML
    public void waveButtonClicked() {
        setMode(LightSettings.Mode.WAVE);
    }

    @FXML
    public void musicButtonClicked() {
            setMode(LightSettings.Mode.MUSIC);
    }

    @FXML
    public void scanButtonClicked() {
        setMode(LightSettings.Mode.SCAN);
    }

    @FXML
    public void strobeButtonClicked() {
        setMode(LightSettings.Mode.STROBE);
    }

    @FXML
    public void offButtonClicked() {
        setMode(LightSettings.Mode.OFF);
    }

    @FXML
    public void disableButtonClicked() {
        setMode(LightSettings.Mode.DISABLE);
    }

    private void setMode(LightSettings.Mode mode) {
        Chromolite.getInstance().getSettings().setMode(mode);
    }
}
