package com.leytonblackler.chromolite.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Pane spectrumPane;

    @FXML
    private Pane colourButtonsPane;

    @FXML
    private Pane modesPane;

    @FXML
    private Pane modeSettingsPane;

    @FXML
    private Pane generalOptionsPane;

    @FXML
    private Pane appConnectPane;

    @FXML
    public ImageView logo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setPaneFromFXML(spectrumPane, "view/Spectrum.fxml");
        setPaneFromFXML(colourButtonsPane, "view/ColourButtons.fxml");
        setPaneFromFXML(modesPane, "view/Modes.fxml");
        setPaneFromFXML(modeSettingsPane, "view/settings/CycleSettings.fxml");
        setPaneFromFXML(generalOptionsPane, "view/GeneralOptions.fxml");
        setPaneFromFXML(appConnectPane, "view/AppConnect.fxml");

        logo.setImage(new Image(getClass().getClassLoader().getResource("images/logo.png").toExternalForm()));

        //r1.setStyle("-fx-fill: red;");
        //r2.setStyle("-fx-fill: #00ff00;");
        //r3.setStyle("-fx-fill: blue;");

        String fontPath = getClass().getClassLoader().getResource("fonts/Roboto-Bold.ttf").toExternalForm();
        fontPath = fontPath.replaceAll("%20", " ");
        Font.loadFont(fontPath, 10);
    }

    private void setPaneFromFXML(Pane pane, String pathToFXML) {
        try {
            Node node = FXMLLoader.load(getClass().getClassLoader().getResource(pathToFXML));
            pane.getChildren().clear();
            pane.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}