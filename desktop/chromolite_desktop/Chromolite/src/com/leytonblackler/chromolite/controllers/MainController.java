package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.main.settings.SettingsObserver;
import com.leytonblackler.chromolite.main.settings.Settings;
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

public class MainController extends SettingsObserver implements Initializable {

    @FXML
    private Pane spectrumPane;

    Controller spectrumController;

    @FXML
    private Pane colourButtonsPane;

    Controller coloursButtonsController;

    @FXML
    private Pane modesPane;

    Controller modeController;

    @FXML
    private Pane modeSettingsPane;

    Controller modeSettingsController;

    @FXML
    private Pane generalOptionsPane;

    Controller generalOptionsController;

    @FXML
    private Pane appConnectPane;

    Controller appConnectController;

    @FXML
    private Pane ledStripSimulationPane;

    Controller ledStripSimulationController;

    @FXML
    public ImageView logo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Chromolite.getInstance().getSettings().addObserver(this);
        
        spectrumController = loadFXMLPane(spectrumPane, "view/Spectrum.fxml");
        coloursButtonsController = loadFXMLPane(colourButtonsPane, "view/ColourButtons.fxml");
        modeController = loadFXMLPane(modesPane, "view/Modes.fxml");
        modeSettingsController = loadFXMLPane(modeSettingsPane, "view/settings/CycleSettings.fxml");
        generalOptionsController = loadFXMLPane(generalOptionsPane, "view/GeneralOptions.fxml");
        appConnectController = loadFXMLPane(appConnectPane, "view/AppConnect.fxml");

        logo.setImage(new Image(getClass().getClassLoader().getResource("images/logo.png").toExternalForm()));

        //r1.setStyle("-fx-fill: red;");
        //r2.setStyle("-fx-fill: #00ff00;");
        //r3.setStyle("-fx-fill: blue;");

        String fontPath = getClass().getClassLoader().getResource("fonts/Roboto-Bold.ttf").toExternalForm();
        fontPath = fontPath.replaceAll("%20", " ");
        Font.loadFont(fontPath, 10);
    }

    private Controller loadFXMLPane(Pane pane, String pathToFXML) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
        try {
            Node node = fxmlLoader.load();
            pane.getChildren().clear();
            pane.getChildren().add(node);
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