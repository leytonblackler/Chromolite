package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.main.Chromolite;
import com.leytonblackler.chromolite.main.settings.Settings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;

public class ModeController implements Controller, Initializable {

    @FXML
    private ToggleButton staticButton;

    @FXML
    private ToggleButton randomButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

    @Override
    public void update(Settings settings) {
        System.out.println(settings.getBrightness());
    }

    @FXML
    public void staticButtonClicked() {
        Chromolite.getInstance().getSettings().setMode(Settings.Mode.STATIC);
    }

    @FXML
    public void randomButtonClicked() {
        //
        System.out.println("random");
        randomButton.setSelected(true);
        staticButton.setSelected(false);
    }
}
