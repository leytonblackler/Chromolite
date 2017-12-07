package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.main.settings.Settings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

public class ColourButtonsController implements Controller, Initializable {

    @FXML
    private ToggleButton primaryButton;

    @FXML
    private ToggleButton secondaryButton;

    @FXML
    private ToggleButton tertiaryButton;

    private Set<ToggleButton> buttons;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new HashSet<>();
        buttons.add(primaryButton);
        buttons.add(secondaryButton);
        buttons.add(tertiaryButton);
    }

    @Override
    public void update(Settings settings) {
        buttons.forEach((button) -> setButtonState(button, settings.getColourSelector()));

        setButtonColour(primaryButton, settings.getPrimaryColour());
        setButtonColour(secondaryButton, settings.getSecondaryColour());
        setButtonColour(tertiaryButton, settings.getTertiaryColour());
    }

    private void setButtonColour(ToggleButton button, int[] colour) {
        button.setStyle("-fx-background-color: rgb(" + colour[0] + "," + colour[1] + "," + colour[2] + ");"
                + "-fx-text-fill: #1c2939;");
    }

    /**
     * Sets the selected state of the button based on the given colour selector.
     * @param button The button to modify the state of.
     * @param colourSelector The mode to determine the state from.
     */
    private void setButtonState(ToggleButton button, Settings.ColourSelector colourSelector) {
        button.setSelected(button.getText().equals(colourSelector.toString()));
    }
}
