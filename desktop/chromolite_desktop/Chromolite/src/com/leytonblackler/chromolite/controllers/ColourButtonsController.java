package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.main.settings.Settings;
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
            setButtonColour(button, colours[index], hover[index]);
        });
        button.setOnMouseExited(e -> {
            hover[index] = false;
            setButtonColour(button, colours[index], hover[index]);
        });
    }

    @Override
    public void update(Settings settings) {
        buttons.forEach((button) -> setButtonState(button, settings.getColourSelector()));

        colours[0] = settings.getPrimaryColour();
        colours[1] = settings.getSecondaryColour();
        colours[2] = settings.getTertiaryColour();

        setButtonColour(primaryButton, colours[0], hover[0]);
        setButtonColour(secondaryButton, colours[1], hover[1]);
        setButtonColour(tertiaryButton, colours[2], hover[2]);
    }

    private void setButtonColour(ToggleButton button, int[] colour, boolean hover) {
        int[] main, accent;
        if (hover) {
            main = calculateAccentColour(colour);
            accent = colour;
        } else {
            main = colour;
            accent = calculateAccentColour(colour);
        }
        button.setStyle("-fx-background-color: rgb(" + main[0] + "," + main[1] + "," + main[2] + ");"
                + "-fx-text-fill: rgb(" + accent[0] + "," + accent[1] + "," + accent[2] + ");");
    }

    private int[] calculateAccentColour(int[] colour) {
        //Convert the given colour from RGB to HSB.
        float[] accentHSB = Color.RGBtoHSB(colour[0], colour[1], colour[2], null);
        //Create the array for the RGB accent color values.
        int[] accentRGB = new int[3];
        //If the brightness is in the upper 50% range, accent is darker.
        if (accentHSB[2] > 0.5) {
            accentHSB[2] = accentHSB[2] / 2;
        } else if (accentHSB[2] < 0.1) {
            accentRGB[0] = 188;
            accentRGB[1] = 196;
            accentRGB[2] = 204;
            return accentRGB;
        } else {
            accentHSB[2] = accentHSB[2] * 2;
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
    private void setButtonState(ToggleButton button, Settings.ColourSelector colourSelector) {
        button.setSelected(button.getText().equals(colourSelector.toString()));
    }
}
