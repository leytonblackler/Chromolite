package com.leytonblackler.chromolite.view;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.controllers.Controller;
import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.main.settings.SettingsObserver;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import java.io.IOException;

public class GUI extends SettingsObserver {

    public static double SCALE = 1;

    private Pane titleBar;
    private double xOffset;
    private double yOffset;

    private Controller spectrumController;

    private Controller coloursButtonsController;

    private Controller modeController;

    private Controller platformsController;

    private Controller generalSettingsController;

    private Controller appConnectController;

    private Controller ledStripSimulationController;

    /*
    Mode Settings
     */

    private Pane modeSettingsPaneContainer;

    private Controller staticSettingsController;
    private Pane staticSettingsPane;

    private Controller cycleSettingsController;
    private Pane cycleSettingsPane;

    private Controller waveSettingsController;
    private Pane waveSettingsPane;

    private Pane disableSettingsPane;

    public GUI(Stage stage) {
        //Enhances the smoothness of text rendering.
        System.setProperty("prism.lcdtext", "false");
        System.setProperty("prism.text", "t2k");

        //Load the Roboto Bold font.
        String fontPath = getClass().getClassLoader().getResource("fonts/Roboto-Bold.ttf").toExternalForm();
        fontPath = fontPath.replaceAll("%20", " ");
        Font.loadFont(fontPath, 10);

        Scene scene;

        //Create a scene with a shadow if the operating system is Windows, since unlike macOS
        //and Linux, Windows does not apply a shadow to undecorated windows.
        String osName = System.getProperty("os.name");
        if (osName != null && osName.startsWith("Windows")) {
            scene = createShadowedScene(createSceneContents());
            stage.initStyle(StageStyle.TRANSPARENT);

        } else {
            scene = new Scene(createSceneContents());
            stage.initStyle(StageStyle.UNDECORATED);
        }

        //Apply the CSS styling to the scene (window contents).
        scene.getStylesheets().add(getClass().getClassLoader().getResource("view/Style.css").toExternalForm());

        Chromolite.getInstance().setLEDStripSimulation((LEDStripSimulationController) ledStripSimulationController);
        //Set the window icon.
        stage.getIcons().add(new Image(getClass().getClassLoader().getResource("images/icon.png").toExternalForm()));
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
        enableWindowDragging(titleBar);
        contents.getChildren().add(titleBar);

        spectrumController = loadFXMLPane(contents, "view/Spectrum.fxml").getValue();
        addTitle(controlPane, "COLOURS");
        coloursButtonsController = loadFXMLPane(controlPane, "view/ColourButtons.fxml").getValue();
        addTitle(controlPane, "MODES");
        modeController = loadFXMLPane(controlPane, "view/Modes.fxml").getValue();

        loadModeSettingsPanes();

        modeSettingsPaneContainer = new StackPane();
        modeSettingsPaneContainer.getChildren().add(staticSettingsPane);
        addTitle(controlPane, "MODE SETTINGS");
        controlPane.getChildren().add(modeSettingsPaneContainer);

        addTitle(controlPane, "PLATFORMS");
        platformsController = loadFXMLPane(controlPane, "view/Platforms.fxml").getValue();
        addTitle(controlPane, "GENERAL SETTINGS");
        generalSettingsController = loadFXMLPane(controlPane, "view/GeneralSettings.fxml").getValue();
        addTitle(controlPane, "ANDROID APP CONNECTION");
        appConnectController = loadFXMLPane(controlPane, "view/AppConnect.fxml").getValue();
        addTitle(controlPane, "LED STRIP SIMULATION");
        ledStripSimulationController = loadFXMLPane(controlPane, "view/LEDStripSimulation.fxml").getValue();
        contents.getChildren().add(controlPane);

        return contents;
    }

    private void loadModeSettingsPanes() {
        Pair<Pane, Controller> staticSettings = loadFXMLPane(null, "view/settings/StaticSettings.fxml");
        staticSettingsController = staticSettings.getValue();
        staticSettingsPane = staticSettings.getKey();

        Pair<Pane, Controller> cycleSettings = loadFXMLPane(null, "view/settings/CycleSettings.fxml");
        cycleSettingsController = cycleSettings.getValue();
        cycleSettingsPane = cycleSettings.getKey();

        Pair<Pane, Controller> waveSettings = loadFXMLPane(null, "view/settings/WaveSettings.fxml");
        waveSettingsController = waveSettings.getValue();
        waveSettingsPane = waveSettings.getKey();

        Pair<Pane, Controller> disableSettings = loadFXMLPane(null, "view/settings/DisableSettings.fxml");
        disableSettingsPane = disableSettings.getKey();
    }

    private Scene createShadowedScene(Parent contents) {
        VBox outer = new VBox();
        outer.getChildren().add(contents);
        outer.setPadding(new Insets(10.0d));
        outer.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), new CornerRadii(0), new Insets(0))));
        contents.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(0, 0, 0, 0.3), 10, 0.5, 0.0, 0.0));
        ((VBox) contents).setBackground(new Background(new BackgroundFill(Color.WHITE, new CornerRadii(0), new Insets(0))));
        Scene scene = new Scene(outer);
        scene.setFill(Color.rgb(0, 255, 0, 0));
        return scene;
    }

    /**
     * Creates a title bar for the window which allows the window to be dragged, closed and minimised.
     * @return The title bar in the form of a Pane.
     */
    private Pane createTitleBar() {
        //Load the Chromolite logo.
        ImageView logo = new ImageView();
        logo.setImage(new Image(getClass().getClassLoader().getResource("images/logo.png").toExternalForm()));
        logo.setPreserveRatio(true);
        //Create a pane for the title bar, where the logo and buttons will be stacked.
        StackPane bar = new StackPane();
        bar.setPrefHeight(2 * Constants.PADDING.getValue());
        //Create a box to use as a container for the logo.
        HBox logoBox = new HBox();
        logoBox.setAlignment(Pos.CENTER);
        logoBox.getChildren().add(logo);
        //Add the logo box containing the logo to the title bar.
        bar.getChildren().add(logoBox);
        //Create a box to use as a container for the close and minimise buttons.
        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.CENTER_RIGHT);
        //Create a minimise button.
        buttonBox.getChildren().add(createMinimiseButton(bar.getPrefHeight()));
        //Create a close button.
        buttonBox.getChildren().add(createCloseButton(bar.getPrefHeight()));
        //Add the button box containing the buttons to the title bar.
        bar.getChildren().add(buttonBox);

        return bar;
    }

    /**
     * Creates a button that allows the window it is contained within to be closed.
     * @param size The width and height of the button.
     * @return A button that allows the window it is contained within to be closed.
     */
    private Button createCloseButton(double size) {
        Button button = new Button();
        //Close the window when the button is clicked.
        button.setOnAction(e -> ((Stage) button.getScene().getWindow()).close());
        //Set the button as a square based on the size.
        button.setPrefWidth(size);
        button.setPrefHeight(size);
        //Apply the CSS styling to the button.
        button.setId("title-bar-button");
        //Create a cross to use as the icon for the button.
        StackPane closeIcon = new StackPane();
        //Create the lines that make up the cross.
        Line closeLine1 = new Line(0, 0, 8, 8);
        Line closeLine2 = new Line(0, 8, 8, 0);
        //Apply the CSS styling to the lines.
        closeLine1.setId("title-bar-button-shape");
        closeLine2.setId("title-bar-button-shape");
        //Add the lines to the close icon stack pane.
        closeIcon.getChildren().add(closeLine1);
        closeIcon.getChildren().add(closeLine2);
        //Set the cross as the close button graphic.
        button.setGraphic(closeIcon);

        return button;
    }

    /**
     * Creates a button that allows the window it is contained within to be minimised.
     * @param size The width and height of the button.
     * @return A button that allows the window it is contained within to be minimised.
     */
    private Button createMinimiseButton(double size) {
        Button button = new Button();
        //Minimise the window when the button is clicked.
        button.setOnAction(e -> ((Stage) button.getScene().getWindow()).setIconified(true));
        //Set the button as a square based on the size.
        button.setPrefWidth(size);
        button.setPrefHeight(size);
        //Apply the CSS styling to the button.
        button.setId("title-bar-button");
        //Create a horizontal line to use as the icon for the button.
        StackPane minimiseIcon = new StackPane();
        //Create the line that makes up the icon.
        Line minimiseLine = new Line(0, 4, 8, 4);
        //Apply the CSS styling to the line.
        minimiseLine.setId("title-bar-button-shape");
        minimiseIcon.getChildren().add(minimiseLine);
        //Set the line as the minimise button graphic.
        button.setGraphic(minimiseIcon);

        return button;
    }

    /**
     * Enables the window that the pane is contained within to be moved by dragging the pane.
     * @param pane The draggable pane.
     */
    private void enableWindowDragging(Pane pane) {
        pane.setOnMousePressed(e -> {
            xOffset = pane.getScene().getWindow().getX() - e.getScreenX();
            yOffset = pane.getScene().getWindow().getY() - e.getScreenY();
        });
        pane.setOnMouseDragged(e -> {
            pane.getScene().getWindow().setX(e.getScreenX() + xOffset);
            pane.getScene().getWindow().setY(e.getScreenY() + yOffset);
        });
    }

    /**
     * Loads a node from an FXML file and adds it as a child to an existing pane.
     *
     * @param pane       The parent pane to add the loaded node to (optional).
     * @param pathToFXML The file path to the FXML file to load.
     * @return The controller for the
     */
    private Pair<Pane, Controller> loadFXMLPane(Pane pane, String pathToFXML) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(pathToFXML));
        try {
            Node node = fxmlLoader.load();
            if (pane != null) {
                pane.getChildren().add(node);
            }
            return new Pair<>((Pane) node, fxmlLoader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalStateException();
    }

    private void addTitle(Pane pane, String title) {
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER_LEFT);
        Label label = new Label(title);
        label.setId("section-title-label");
        label.translateYProperty().setValue(15 / 2); //FXML PAD VALUE USED HERE, NEEDS DYNAMIC SOLUTION
        titleBox.getChildren().add(label);
        pane.getChildren().add(titleBox);
    }

    @Override
    public void updateSpectrum(SettingsManager settings) {
        spectrumController.update(settings);
    }

    @Override
    public void updateColours(SettingsManager settings) {
        coloursButtonsController.update(settings);
    }

    @Override
    public void updateModes(SettingsManager settings) {
        modeController.update(settings);
    }

    @Override
    public void updateModeSettings(SettingsManager settings) {
        switch (settings.getMode()) {
            case STATIC:
                setModeSettingsPane(staticSettingsPane, staticSettingsController, settings);
                break;
            case CYCLE:
                setModeSettingsPane(cycleSettingsPane, cycleSettingsController, settings);
                break;
            case WAVE:
                setModeSettingsPane(waveSettingsPane, waveSettingsController, settings);
                break;
            case DISABLE:
                setModeSettingsPane(disableSettingsPane, null, settings);
                break;
        }
    }

    private void setModeSettingsPane(Pane pane, Controller controller, SettingsManager settings) {
        modeSettingsPaneContainer.getChildren().clear();
        modeSettingsPaneContainer.getChildren().add(pane);
        if (controller != null) {
            controller.update(settings);
        }
    }

    @Override
    public void updatePlatformSettings(SettingsManager settings) {
        platformsController.update(settings);
    }

    @Override
    public void updateGeneralSettings(SettingsManager settings) {
        generalSettingsController.update(settings);
    }

    @Override
    public void updateAndroidAppConnection(SettingsManager settings) {
        appConnectController.update(settings);
    }
}
