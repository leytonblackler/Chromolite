package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.main.settings.SettingsManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AppConnectController implements Controller, Initializable {

    @FXML
    public ImageView networkIcon;

    @FXML
    public ImageView bluetoothIcon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        networkIcon.setImage(new Image(getClass().getClassLoader().getResource("images/network_icon.png").toExternalForm()));
        bluetoothIcon.setImage(new Image(getClass().getClassLoader().getResource("images/bluetooth_icon.png").toExternalForm()));
    }

    @Override
    public void update(SettingsManager settings) {
        //
    }
}
