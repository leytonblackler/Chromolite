package com.leytonblackler.chromolite.view;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.controllers.Controller;
import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.settings.SettingsObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class GUI extends SettingsObserver {

    public static double SCALE = 1;

    private VBox mainPane;

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

    public GUI(Stage stage) {
        //Create the scene (window contents) from the main FXML file.
        //Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("view/View.fxml")));
        Scene scene = createScene();
        //Apply the CSS styling to the scene (window contents).
        scene.getStylesheets().add(getClass().getClassLoader().getResource("view/Style.css").toExternalForm());

        String fontPath = getClass().getClassLoader().getResource("fonts/Roboto-Bold.ttf").toExternalForm();
        fontPath = fontPath.replaceAll("%20", " ");
        Font.loadFont(fontPath, 10);

        Chromolite.getInstance().setLEDStripSimulation((LEDStripSimulationController) ledStripSimulationController);

        //Initialise the stage (window).
        stage.setTitle("Chromolite");
        //stage.setWidth(1200);
        //stage.setHeight(800);
        //stage.setResizable(false);
        stage.setScene(scene);
        //Display the stage (window).
        stage.show();
        stage.sizeToScene();

        System.out.println(stage.getWidth() + " " + stage.getHeight());
    }

    private Scene createScene() {
        mainPane = new VBox();
        mainPane.spacingProperty().bind(Constants.PADDING);

        Scene scene = new Scene(mainPane);

        VBox controlPane = new VBox();
        controlPane.spacingProperty().bind(Constants.PADDING);
        //controlPane.paddingProperty().bind(Constants.PADDING);
        controlPane.setPadding(new Insets(0, Constants.PADDING.doubleValue(), Constants.PADDING.doubleValue(), Constants.PADDING.doubleValue())); // <-- BIND THIS

        mainPane.getChildren().add(createHeader());

        spectrumController = loadFXMLPane(mainPane, "view/Spectrum.fxml", null);
        coloursButtonsController = loadFXMLPane(controlPane, "view/ColourButtons.fxml", "COLOURS");
        modeController = loadFXMLPane(controlPane, "view/Modes.fxml", "MODES");
        modeSettingsController = loadFXMLPane(controlPane, "view/settings/CycleSettings.fxml", "MODE SETTINGS");
        generalOptionsController = loadFXMLPane(controlPane, "view/GeneralOptions.fxml", "GENERAL OPTIONS");
        appConnectController = loadFXMLPane(controlPane, "view/AppConnect.fxml", "ANDROID APP CONNECTION");
        ledStripSimulationController = loadFXMLPane(controlPane, "view/LEDStripSimulation.fxml", "LED STRIP SIMULATION");
        mainPane.getChildren().add(controlPane);

        return scene;
    }

    private HBox createHeader() {
        HBox horizontal = new HBox();
        horizontal.setAlignment(Pos.CENTER);

        ImageView logo = new ImageView();
        logo.setImage(new Image(getClass().getClassLoader().getResource("images/logo.png").toExternalForm()));
        logo.setPreserveRatio(true);

        /*VBox vertical = new VBox();
        vertical.setAlignment(Pos.CENTER);
        vertical.setPrefHeight(2 * Constants.PADDING);
        vertical.getChildren().add(logo);*/

        horizontal.getChildren().add(logo);
        return horizontal;
    }

    /**
     * Loads a node from an FXML file and adds it as a child to an existing pane.
     *
     * @param pane       The parent pane to add the loaded node to.
     * @param pathToFXML The file path to the FXML file to load.
     * @param title      An optional title to give to add above the loaded node (null if no title required).
     * @return The controller for the
     */
    private Controller loadFXMLPane(Pane pane, String pathToFXML, String title) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
        try {
            //Adds a title above the panel, if a title was supplied.
            if (title != null) {
                HBox titleBox = new HBox();
                titleBox.setAlignment(Pos.CENTER_LEFT);
                Label label = new Label(title);
                label.setId("section-title-label");
                label.translateYProperty().setValue(15 / 2); //FXML PAD VALUE USED HERE, NEEDS DYNAMIC SOLUTION
                titleBox.getChildren().add(label);
                pane.getChildren().add(titleBox);
            }

            Node node = fxmlLoader.load();
            //pane.getChildren().clear();
            //pane.getChildren().add(node);
            pane.getChildren().add(node);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fxmlLoader.getController();
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
