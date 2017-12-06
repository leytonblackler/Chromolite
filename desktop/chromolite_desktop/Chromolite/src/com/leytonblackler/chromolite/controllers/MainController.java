package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.main.settings.SettingsObserver;
import com.leytonblackler.chromolite.main.settings.Settings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController extends SettingsObserver implements Initializable {

    @FXML
    private Pane mainPane;

    //@FXML
    //private Pane spectrumPane;

    Controller spectrumController;

    //@FXML
    //private Pane colourButtonsPane;

    Controller coloursButtonsController;

    //@FXML
    //private HBox modesPane;

    Controller modeController;

    //@FXML
    //private Pane modeSettingsPane;

    Controller modeSettingsController;

    //@FXML
    //private Pane generalOptionsPane;

    Controller generalOptionsController;

    //@FXML
    //private Pane appConnectPane;

    Controller appConnectController;

    //@FXML
    //private Pane ledStripSimulationPane;

    Controller ledStripSimulationController;

    @FXML
    public ImageView logo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Chromolite.getInstance().getSettings().addObserver(this);
        
        spectrumController = loadFXMLPane("view/Spectrum.fxml", null);
        coloursButtonsController = loadFXMLPane("view/ColourButtons.fxml", "COLOURS");
        modeController = loadFXMLPane("view/Modes.fxml", "MODES");
        modeSettingsController = loadFXMLPane("view/settings/CycleSettings.fxml", "MODE SETTINGS");
        generalOptionsController = loadFXMLPane("view/GeneralOptions.fxml", "GENERAL OPTIONS");
        appConnectController = loadFXMLPane("view/AppConnect.fxml", "ANDROID APP CONNECTION");
        ledStripSimulationController = loadFXMLPane("view/LEDStripSimulation.fxml", "LED STRIP SIMULATION");
        Chromolite.getInstance().setLEDStripSimulation((LEDStripSimulationController) ledStripSimulationController);

        logo.setImage(new Image(getClass().getClassLoader().getResource("images/logo.png").toExternalForm()));

        //r1.setStyle("-fx-fill: red;");
        //r2.setStyle("-fx-fill: #00ff00;");
        //r3.setStyle("-fx-fill: blue;");

        String fontPath = getClass().getClassLoader().getResource("fonts/Roboto-Bold.ttf").toExternalForm();
        fontPath = fontPath.replaceAll("%20", " ");
        Font.loadFont(fontPath, 10);
    }

    private Controller loadFXMLPane(String pathToFXML, String title) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
        try {
            //Adds a title above the panel, if a title was supplied.
            if (title != null) {
                HBox titleBox = new HBox();
                titleBox.setAlignment(Pos.CENTER_LEFT);
                Label label = new Label(title);
                label.setId("section-title-label");
                label.translateYProperty().setValue(15 / 2); //FXML PADDING VALUE USED HERE, NEEDS DYNAMIC SOLUTION
                titleBox.getChildren().add(label);
                mainPane.getChildren().add(titleBox);
            }

            Node node = fxmlLoader.load();
            //pane.getChildren().clear();
            //pane.getChildren().add(node);
            mainPane.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fxmlLoader.getController();
    }

    @Override
    public void update(Settings settings) {
        super.update(settings);
        /*if (ledStripSimulationController == null) {
            ledStripSimulationController = loadFXMLPane(ledStripSimulationPane, "view/LEDStripSimulation.fxml");
            ((LEDStripSimulationController) ledStripSimulationController).setLEDStripWidth(ledStripSimulationPane.getWidth());
        }*/
    }

    @Override
    public void updateSpectrum(Settings settings) {
        spectrumController.update(settings);
    }

    @Override
    public void updateColours(Settings settings) {
        coloursButtonsController.update(settings);
    }

    @Override
    public void updateModes(Settings settings) {
        modeController.update(settings);
    }

    @Override
    public void updateModeSettings(Settings settings) {
        modeSettingsController.update(settings);
    }

    @Override
    public void updateGeneralOptions(Settings settings) {
        generalOptionsController.update(settings);
    }

    @Override
    public void updateAndroidAppConnection(Settings settings) {
        appConnectController.update(settings);
    }
}