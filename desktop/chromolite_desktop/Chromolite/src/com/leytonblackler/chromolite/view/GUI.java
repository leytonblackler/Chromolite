package com.leytonblackler.chromolite.view;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.controllers.Controller;
import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.settings.Settings;
import com.leytonblackler.chromolite.main.settings.SettingsObserver;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GUI extends SettingsObserver {

    public static double SCALE = 1;

    private static int WINDOW_BORDER = 3;

    private Pane titleBar;
    private double xOffset;
    private double yOffset;

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
        //Create the scene (window contents).
        //Scene scene = new Scene(FXMLLoader.load(getClass().getClassLoader().getResource("view/View.fxml")));

        String fontPath = getClass().getClassLoader().getResource("fonts/Roboto-Bold.ttf").toExternalForm();
        fontPath = fontPath.replaceAll("%20", " ");
        Font.loadFont(fontPath, 10);

        Scene scene;

        //Create a scene with a shadow if the operating system is Windows, since unlike macOS
        //and Linux, Windows does not apply a shadow to undecorated windows.
        String osName = System.getProperty("os.name");
        if(osName != null && osName.startsWith("Windows") ) {
            scene = createShadowedScene(createSceneContents());
            stage.initStyle(StageStyle.TRANSPARENT);

        } else {
            scene = new Scene(createSceneContents());
            stage.initStyle(StageStyle.UNDECORATED);
        }

        enableWindowDragging(stage, titleBar);

        //Apply the CSS styling to the scene (window contents).
        scene.getStylesheets().add(getClass().getClassLoader().getResource("view/Style.css").toExternalForm());

        Chromolite.getInstance().setLEDStripSimulation((LEDStripSimulationController) ledStripSimulationController);

        //Add the window contents to the window.
        stage.setScene(scene);
        //Set the window as transparent to remove window borders and title.
        stage.initStyle(StageStyle.TRANSPARENT);
        //Do not allow the window to be resized by the user.
        stage.setResizable(false);
        //Display the window.
        stage.show();
        //Resize the window to fit the contents correctly.
        stage.sizeToScene();
    }

    private Parent createSceneContents() {
        VBox contents = new VBox();
        contents.setId("root-pane");

        VBox controlPane = new VBox();
        controlPane.spacingProperty().bind(Constants.PADDING);
        //controlPane.paddingProperty().bind(Constants.PADDING);
        controlPane.setPadding(new Insets(0, Constants.PADDING.getValue(), Constants.PADDING.getValue(), Constants.PADDING.getValue())); // <-- BIND THIS

        titleBar = createTitleBar();
        contents.getChildren().add(titleBar);

        spectrumController = loadFXMLPane(contents, "view/Spectrum.fxml", null);
        coloursButtonsController = loadFXMLPane(controlPane, "view/ColourButtons.fxml", "COLOURS");
        modeController = loadFXMLPane(controlPane, "view/Modes.fxml", "MODES");
        modeSettingsController = loadFXMLPane(controlPane, "view/settings/CycleSettings.fxml", "MODE SETTINGS");
        generalOptionsController = loadFXMLPane(controlPane, "view/GeneralOptions.fxml", "GENERAL OPTIONS");
        appConnectController = loadFXMLPane(controlPane, "view/AppConnect.fxml", "ANDROID APP CONNECTION");
        ledStripSimulationController = loadFXMLPane(controlPane, "view/LEDStripSimulation.fxml", "LED STRIP SIMULATION");
        contents.getChildren().add(controlPane);

        return contents;
    }

    private Scene createShadowedScene(Parent contents) {
        VBox outer = new VBox();
        outer.getChildren().add(contents);
        outer.setPadding(new Insets(10.0d));
        outer.setBackground( new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), new CornerRadii(0), new Insets(0))));
        contents.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.4), 10, 0.5, 0.0, 0.0));
        ((VBox) contents).setBackground( new Background(new BackgroundFill( Color.WHITE, new CornerRadii(0), new Insets(0))));
        Scene scene = new Scene(outer);
        scene.setFill(Color.rgb(0, 255, 0, 0));
        return scene;
    }

    private HBox createTitleBar() {
        ImageView logo = new ImageView();
        logo.setImage(new Image(getClass().getClassLoader().getResource("images/logo.png").toExternalForm()));
        logo.setPreserveRatio(true);
        HBox horizontal = new HBox();
        horizontal.setAlignment(Pos.CENTER);
        horizontal.setPrefHeight(2 * Constants.PADDING.getValue());
        horizontal.getChildren().add(logo);
        return horizontal;
    }

    private void enableWindowDragging(Stage window, Pane pane) {
        pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = window.getX() - event.getScreenX();
                yOffset = window.getY() - event.getScreenY();
            }
        });

        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                window.setX(event.getScreenX() + xOffset);
                window.setY(event.getScreenY() + yOffset);
            }
        });
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
