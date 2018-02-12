package com.leytonblackler.chromolite.view.windows;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.controllers.Controller;
import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import com.leytonblackler.chromolite.view.Constants;
import com.leytonblackler.chromolite.view.GUIUtilities;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Pair;

public class MainWindow extends Window {

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

    private Controller musicSettingsController;
    private Pane musicSettingsPane;

    private Controller scanSettingsController;
    private Pane scanSettingsPane;

    private Controller strobeSettingsController;
    private Pane strobeSettingsPane;

    private Pane emptySettingsPane;

    public MainWindow(Stage stage) {
        super(stage);
        Chromolite.getInstance().setLEDStripSimulation((LEDStripSimulationController) ledStripSimulationController);
    }

    protected Parent createSceneContents() {
        //Create the scene root pane.
        VBox contents = new VBox();
        //Create the pane used for the control section of the interface.
        VBox controlPane = new VBox();
        controlPane.spacingProperty().bind(Constants.PADDING);
        controlPane.setPadding(new Insets(0, Constants.PADDING.getValue(), Constants.PADDING.getValue(), Constants.PADDING.getValue())); // <-- BIND THIS

        spectrumController = GUIUtilities.loadFXMLPane(contents, "view/Spectrum.fxml").getValue();
        GUIUtilities.addTitle(controlPane, "COLOURS");
        coloursButtonsController = GUIUtilities.loadFXMLPane(controlPane, "view/ColourButtons.fxml").getValue();
        GUIUtilities.addTitle(controlPane, "MODES");
        modeController = GUIUtilities.loadFXMLPane(controlPane, "view/Modes.fxml").getValue();

        loadModeSettingsPanes();

        modeSettingsPaneContainer = new StackPane();
        modeSettingsPaneContainer.getChildren().add(staticSettingsPane);
        GUIUtilities.addTitle(controlPane, "MODE SETTINGS");
        controlPane.getChildren().add(modeSettingsPaneContainer);

        GUIUtilities.addTitle(controlPane, "PLATFORMS");
        platformsController = GUIUtilities.loadFXMLPane(controlPane, "view/Platforms.fxml").getValue();
        GUIUtilities.addTitle(controlPane, "GENERAL SETTINGS");
        generalSettingsController = GUIUtilities.loadFXMLPane(controlPane, "view/GeneralSettings.fxml").getValue();
        GUIUtilities.addTitle(controlPane, "ANDROID APP CONNECTION");
        appConnectController = GUIUtilities.loadFXMLPane(controlPane, "view/AppConnect.fxml").getValue();
        GUIUtilities.addTitle(controlPane, "LED STRIP SIMULATION");
        ledStripSimulationController = GUIUtilities.loadFXMLPane(controlPane, "view/LEDStripSimulation.fxml").getValue();
        contents.getChildren().add(controlPane);

        return contents;
    }

    private void loadModeSettingsPanes() {
        Pair<Pane, Controller> staticSettings = GUIUtilities.loadFXMLPane(null, "view/settings/StaticSettings.fxml");
        staticSettingsController = staticSettings.getValue();
        staticSettingsPane = staticSettings.getKey();

        Pair<Pane, Controller> cycleSettings = GUIUtilities.loadFXMLPane(null, "view/settings/CycleSettings.fxml");
        cycleSettingsController = cycleSettings.getValue();
        cycleSettingsPane = cycleSettings.getKey();

        Pair<Pane, Controller> waveSettings = GUIUtilities.loadFXMLPane(null, "view/settings/WaveSettings.fxml");
        waveSettingsController = waveSettings.getValue();
        waveSettingsPane = waveSettings.getKey();

        /*Pair<Pane, Controller> musicSettings = loadFXMLPane(null, "view/settings/MusicSettings.fxml");
        musicSettingsController = musicSettings.getValue();
        musicSettingsPane = musicSettings.getKey();*/

        Pair<Pane, Controller> scanSettings = GUIUtilities.loadFXMLPane(null, "view/settings/ScanSettings.fxml");
        scanSettingsController = scanSettings.getValue();
        scanSettingsPane = scanSettings.getKey();

        /*Pair<Pane, Controller> strobeSettings = loadFXMLPane(null, "view/settings/StrobeSettings.fxml");
        strobeSettingsController = strobeSettings.getValue();
        strobeSettingsPane = strobeSettings.getKey();*/

        Pair<Pane, Controller> emptySettings = GUIUtilities.loadFXMLPane(null, "view/settings/DisableSettings.fxml");
        emptySettingsPane = emptySettings.getKey();
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
            case MUSIC:
                setModeSettingsPane(musicSettingsPane, musicSettingsController, settings);
                break;
            case SCAN:
                setModeSettingsPane(scanSettingsPane, scanSettingsController, settings);
                break;
            case STROBE:
                setModeSettingsPane(strobeSettingsPane, strobeSettingsController, settings);
                break;
            case OFF:
                setModeSettingsPane(emptySettingsPane, null, settings);
                break;
            case DISABLE:
                setModeSettingsPane(emptySettingsPane, null, settings);
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
