package com.leytonblackler.chromolite.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;

import java.net.URL;
import java.util.ResourceBundle;

public class ModeController implements Initializable {

    @FXML
    private ToggleButton staticButton;

    @FXML
    private ToggleButton randomButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

    @FXML
    public void staticButtonClicked() {
        System.out.println("static");
        staticButton.setSelected(true);
        randomButton.setSelected(false);
    }

    @FXML
    public void randomButtonClicked() {
        //
        System.out.println("random");
        randomButton.setSelected(true);
        staticButton.setSelected(false);
    }
}
