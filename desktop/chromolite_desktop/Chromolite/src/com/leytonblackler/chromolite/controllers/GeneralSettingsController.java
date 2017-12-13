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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
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
}
