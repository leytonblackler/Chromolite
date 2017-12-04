package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.main.settings.Settings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class ModeController implements Controller, Initializable {

    @FXML
    private ToggleButton staticButton;

    @FXML
    private ToggleButton randomButton;

    @FXML
    private ToggleButton waveButton;

    @FXML
    private ToggleButton musicButton;

    @FXML
    private ToggleButton cycleButton;

    @FXML
    private ToggleButton strobeButton;

    @FXML
    private ToggleButton scanButton;

    @FXML
    private ToggleButton offButton;

    private Set<ToggleButton> buttons;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new HashSet<>();
        buttons.add(staticButton);
        buttons.add(randomButton);
        buttons.add(waveButton);
        buttons.add(musicButton);
        buttons.add(cycleButton);
        buttons.add(strobeButton);
        buttons.add(scanButton);
        buttons.add(offButton);
    }

    @Override
    public void update(Settings settings) {
        buttons.forEach((button) -> setButtonState(button, settings.getMode()));
    }

    /**
     * Sets the selected state of the button based on the given mode.
     * @param button The button to modify the state of.
     * @param mode The mode to determine the state from.
     */
    private void setButtonState(ToggleButton button, Settings.Mode mode) {
        button.setSelected(button.getText().equals(mode.toString()));
    }

    @FXML
    public void staticButtonClicked() {
        setMode(Settings.Mode.STATIC);
    }

    @FXML
    public void randomButtonClicked() {
        setMode(Settings.Mode.RANDOM);
    }

    @FXML
    public void waveButtonClicked() {
        setMode(Settings.Mode.WAVE);
    }

    @FXML
    public void musicButtonClicked() {
            setMode(Settings.Mode.MUSIC);
    }

    @FXML
    public void cycleButtonClicked() {
        setMode(Settings.Mode.CYCLE);
    }

    @FXML
    public void strobeButtonClicked() {
        setMode(Settings.Mode.STROBE);
    }

    @FXML
    public void scanButtonClicked() {
        setMode(Settings.Mode.SCAN);
    }

    @FXML
    public void offButtonClicked() {
        setMode(Settings.Mode.OFF);
    }

    private void setMode(Settings.Mode mode) {
        Chromolite.getInstance().getSettings().setMode(mode);
    }
}
