package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.settings.categories.LightSettings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

import javafx.event.EventHandler;

import java.awt.*;
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

    private int[][] colours = new int[3][3];

    private boolean[] hover = { false, false, false };

    private boolean[] pressed = { false, false, false };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons = new HashSet<>();
        buttons.add(primaryButton);
        buttons.add(secondaryButton);
        buttons.add(tertiaryButton);

        setMouseListeners(primaryButton, 0);
        setMouseListeners(secondaryButton, 1);
        setMouseListeners(tertiaryButton, 2);

    }

    private void setMouseListeners(ToggleButton button, int index) {
        button.setOnMouseEntered(e -> {
            hover[index] = true;
            setButtonStyle(button, colours[index], hover[index], pressed[index]);
        });
        button.setOnMouseExited(e -> {
            hover[index] = false;
            setButtonStyle(button, colours[index], hover[index], pressed[index]);
        });
        button.setOnMousePressed(e -> {
            pressed[index] = true;
            setButtonStyle(button, colours[index], hover[index], pressed[index]);
        });
        button.setOnMouseReleased(e -> {
            pressed[index] = false;
            setButtonStyle(button, colours[index], hover[index], pressed[index]);
        });
    }

    @Override
    public void update(SettingsManager settings) {
        buttons.forEach((button) -> setButtonState(button, settings.getColourSelector()));

        colours[0] = settings.getPrimaryColour();
        colours[1] = settings.getSecondaryColour();
        colours[2] = settings.getTertiaryColour();

        setButtonStyle(primaryButton, colours[0], hover[0], pressed[0]);
        setButtonStyle(secondaryButton, colours[1], hover[1], pressed[1]);
        setButtonStyle(tertiaryButton, colours[2], hover[2], pressed[2]);
    }

    private void setButtonStyle(ToggleButton button, int[] colour, boolean hover, boolean pressed) {
        int[] main, text;
        if (hover && !pressed) {
            main = calculateAccentColour(colour, 1.2f);
            text = colour;
        } else {
            main = colour;
            text = calculateAccentColour(colour, 2f);
        }

        double inset = 2;
        if (button.isSelected()) {
            inset = 0;
        }

        button.setStyle("-fx-background-color: rgb(" + main[0] + "," + main[1] + "," + main[2] + ");"
                + "-fx-text-fill: rgb(" + text[0] + "," + text[1] + "," + text[2] + ");"
                + "-fx-background-insets: " + inset + "," + inset + "," + inset + "," + inset + ";"
                + "-fx-background-radius: 2em;");
    }

    private int[] calculateAccentColour(int[] colour, float brightnessChange) {
        //Convert the given colour from RGB to HSB.
        float[] accentHSB = Color.RGBtoHSB(colour[0], colour[1], colour[2], null);
        //Create the array for the RGB accent color values.
        int[] accentRGB = new int[3];
        //If the brightness is in the upper 50% range, accent is darker.
        if (accentHSB[2] > 0.5) {
            accentHSB[2] = accentHSB[2] / brightnessChange;
        } else if (accentHSB[2] < 0.1) {
            accentRGB[0] = 188;
            accentRGB[1] = 196;
            accentRGB[2] = 204;
            return accentRGB;
        } else {
            accentHSB[2] = accentHSB[2] * brightnessChange;
        }
        //Convert the accent colour from HSB to RGB.
        int rgb = Color.HSBtoRGB(accentHSB[0], accentHSB[1], accentHSB[2]);
        accentRGB[0] = (rgb >> 16) & 0xFF;
        accentRGB[1] = (rgb >> 8) & 0xFF;
        accentRGB[2] = rgb & 0xFF;

        return accentRGB;
    }

    /**
     * Sets the selected state of the button based on the given colour selector.
     * @param button The button to modify the state of.
     * @param colourSelector The mode to determine the state from.
     */
    private void setButtonState(ToggleButton button, LightSettings.ColourSelector colourSelector) {
        button.setSelected(button.getText().equals(colourSelector.toString()));
    }

    @FXML
    private void primaryButtonClicked() {
        Chromolite.getInstance().getSettings().setColourSelector(LightSettings.ColourSelector.PRIMARY);
    }

    @FXML
    private void secondaryButtonClicked() {
        Chromolite.getInstance().getSettings().setColourSelector(LightSettings.ColourSelector.SECONDARY);
    }

    @FXML
    private void tertiaryButtonClicked() {
        Chromolite.getInstance().getSettings().setColourSelector(LightSettings.ColourSelector.TERTIARY);
    }
}
