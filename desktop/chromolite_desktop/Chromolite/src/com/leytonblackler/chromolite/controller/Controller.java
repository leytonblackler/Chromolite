package com.leytonblackler.chromolite.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Pane modesPane;

    @FXML
    private Pane modeSettingsPane;

    @FXML
    public ImageView logo;

    @FXML
    public ImageView spectrum;

    @FXML
    public ImageView networkIcon;

    @FXML
    public ImageView bluetoothIcon;

    @FXML
    public ImageView razerLogo;
    private Image[] razerLogoImages = new Image[2];

    @FXML
    public ImageView powerIcon;
    private Image[] powerIconImages = new Image[2];

    @FXML
    private Rectangle r1;

    @FXML
    private Rectangle r2;

    @FXML
    private Rectangle r3;

    @FXML
    private ToggleButton razerButton;

    @FXML
    private ToggleButton offButton;

    @FXML
    private Pane primarySpectrumIndicator, secondarySpectrumIndicator, tertiarySpectrumIndicator;
    private Pane[] spectrumIndicators = new Pane[3];
    private enum SpectrumIndicator { PRIMARY, SECONDARY, TERTIARY }

    @FXML
    private StackPane spectrumStackPane;

    private boolean mouseOverSpectrum = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            Node modes = FXMLLoader.load(getClass().getClassLoader().getResource("view/Modes.fxml"));
            modesPane.getChildren().clear();
            modesPane.getChildren().add(modes);

            Node staticSettings = FXMLLoader.load(getClass().getClassLoader().getResource("view/settings/WaveSettings.fxml"));
            modeSettingsPane.getChildren().clear();
            modeSettingsPane.getChildren().add(staticSettings);
        } catch (IOException e) {
            e.printStackTrace();
        }

        logo.setImage(new Image(getClass().getClassLoader().getResource("images/logo.png").toExternalForm()));

        Image spectrumImage = new Image(getClass().getClassLoader().getResource("images/spectrum.png").toExternalForm(), spectrum.getFitWidth(), spectrum.getFitHeight(), false, true);
        spectrum.setImage(spectrumImage);

        //razerLogoImages[0] = new Image(getClass().getClassLoader().getResource("images/razer_logo_light.png").toExternalForm());
        //razerLogoImages[1] = new Image(getClass().getClassLoader().getResource("images/razer_logo_dark.png").toExternalForm());
        //razerLogo.setImage(razerLogoImages[0]);

        //networkIcon.setImage(new Image(getClass().getClassLoader().getResource("images/network_icon.png").toExternalForm()));
        //bluetoothIcon.setImage(new Image(getClass().getClassLoader().getResource("images/bluetooth_icon.png").toExternalForm()));

        //powerIconImages[0] = new Image(getClass().getClassLoader().getResource("images/power_icon_light.png").toExternalForm());
        //powerIconImages[1] = new Image(getClass().getClassLoader().getResource("images/power_icon_dark.png").toExternalForm());
        //powerIcon.setImage(powerIconImages[0]);

        spectrumIndicators[0] = primarySpectrumIndicator;
        spectrumIndicators[1] = secondarySpectrumIndicator;
        spectrumIndicators[2] = tertiarySpectrumIndicator;

        for (Pane spectrumIndicator : spectrumIndicators) {
            spectrumIndicator.translateXProperty().unbind();
            spectrumIndicator.translateYProperty().unbind();
        }

        //r1.setStyle("-fx-fill: red;");
        //r2.setStyle("-fx-fill: #00ff00;");
        //r3.setStyle("-fx-fill: blue;");

        EventHandler<MouseEvent> spectrumMouseHandler =
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent t) {
                        int x, y;
                        int imageWidth = (int) spectrum.getImage().getWidth();
                        int imageHeight = (int) spectrum.getImage().getHeight();

                        if (t.getX() < 0) {
                            x = 0;
                        } else if (t.getX() >= imageWidth) {
                            x = imageWidth - 1;
                        } else {
                            x = (int) t.getX();
                        }

                        if (t.getY() < 0) {
                            y = 0;
                        } else if (t.getY() >= imageHeight) {
                            y = imageHeight - 1;
                        } else {
                            y = (int) t.getY();
                        }

                        spectrumIndicators[SpectrumIndicator.PRIMARY.ordinal()].translateXProperty().setValue(x);
                        spectrumIndicators[SpectrumIndicator.PRIMARY.ordinal()].translateYProperty().setValue(y);

                        int[] rgb = colorToRGB(spectrum.getImage().getPixelReader().getColor(x, y));
                        setSpectrumIndicatorColor(SpectrumIndicator.PRIMARY, rgb);
                    }
                };

        spectrumStackPane.setOnMousePressed(spectrumMouseHandler);
        spectrumStackPane.setOnMouseDragged(spectrumMouseHandler);

        String fontPath = getClass().getClassLoader().getResource("fonts/Roboto-Bold.ttf").toExternalForm();
        fontPath = fontPath.replaceAll("%20", " ");
        Font.loadFont(fontPath, 10);
    }

    private int[] colorToRGB(Color color) {
        int[] rgb = new int[3];
        rgb[0] = (int) (color.getRed() * 255);
        rgb[1] = (int) (color.getGreen() * 255);
        rgb[2] = (int) (color.getBlue() * 255);
        return rgb;
    }

    // @requires rgb[0] >= 0 && rgb[0] <= 255 && rgb[1] >= 0 && rgb[1] <= 255 && rgb[2] >= 0 && rgb[2] <= 255
    private void setSpectrumIndicatorColor(SpectrumIndicator indicator, int[] rgb) {
        if (rgb[0] < 0 || rgb[0] > 255 || rgb[1] < 0 || rgb[1] > 255 || rgb[2] < 0 || rgb[2] > 255) {
            throw new IllegalArgumentException();
        }
        (((StackPane) spectrumIndicators[indicator.ordinal()].getChildren().get(0)).getChildren().get(1)).setStyle("-fx-fill: rgb(" + rgb[0] + "," + rgb[1] + "," + rgb[2] + ");");
    }

    @FXML
    public void razerButtonClicked() {
        //
        System.out.println("razer");
        if (razerButton.selectedProperty().get()) {
            razerLogo.setImage(razerLogoImages[1]);
        } else {
            razerLogo.setImage(razerLogoImages[0]);
        }
    }

    @FXML
    public void offButtonClicked() {
        //
        System.out.println("off");
        if (offButton.selectedProperty().get()) {
            powerIcon.setImage(powerIconImages[1]);
        } else {
            powerIcon.setImage(powerIconImages[0]);
        }
    }
}