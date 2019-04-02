package com.leytonblackler.chromolite.controllers;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class SpectrumController extends Controller implements Initializable {

    @FXML
    public ImageView spectrum;

    @FXML
    private Pane primarySpectrumIndicator, secondarySpectrumIndicator, tertiarySpectrumIndicator;
    private Pane[] spectrumIndicators = new Pane[3];

    private enum SpectrumIndicator { PRIMARY, SECONDARY, TERTIARY }

    @FXML
    private StackPane spectrumStackPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Image spectrumImage = new Image(getClass().getClassLoader().getResource("images/spectrum.png").toExternalForm(), spectrum.getFitWidth(), spectrum.getFitHeight(), false, true);
        spectrum.setImage(spectrumImage);

        spectrumIndicators[0] = primarySpectrumIndicator;
        spectrumIndicators[1] = secondarySpectrumIndicator;
        spectrumIndicators[2] = tertiarySpectrumIndicator;

        for (Pane spectrumIndicator : spectrumIndicators) {
            spectrumIndicator.translateXProperty().unbind();
            spectrumIndicator.translateYProperty().unbind();
        }

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

                        SettingsManager settings = getSettings();

                        int[] colour = colorToRGB(spectrum.getImage().getPixelReader().getColor(x, y));
                        switch (settings.getColourSelector()) {
                            case PRIMARY:
                                settings.setPrimaryColour(colour);
                                settings.setPrimaryIndicatorPosition(x, y);
                                break;
                            case SECONDARY:
                                settings.setSecondaryColour(colour);
                                settings.setSecondaryIndicatorPosition(x, y);
                                break;
                            case TERTIARY:
                                settings.setTertiaryColour(colour);
                                settings.setTertiaryIndicatorPosition(x, y);
                                break;
                        }
                    }
                };

        spectrumStackPane.setOnMousePressed(spectrumMouseHandler);
        spectrumStackPane.setOnMouseDragged(spectrumMouseHandler);
    }

    @Override
    public void update(SettingsManager settings) {
        spectrumIndicators[0].translateXProperty().setValue(settings.getPrimaryIndicatorPosition()[0]);
        spectrumIndicators[0].translateYProperty().setValue(settings.getPrimaryIndicatorPosition()[1]);
        spectrumIndicators[1].translateXProperty().setValue(settings.getSecondaryIndicatorPosition()[0]);
        spectrumIndicators[1].translateYProperty().setValue(settings.getSecondaryIndicatorPosition()[1]);
        spectrumIndicators[2].translateXProperty().setValue(settings.getTertiaryIndicatorPosition()[0]);
        spectrumIndicators[2].translateYProperty().setValue(settings.getTertiaryIndicatorPosition()[1]);

        setSpectrumIndicatorColor(primarySpectrumIndicator, settings.getPrimaryColour());
        setSpectrumIndicatorColor(secondarySpectrumIndicator, settings.getSecondaryColour());
        setSpectrumIndicatorColor(tertiarySpectrumIndicator, settings.getTertiaryColour());
    }

    private int[] colorToRGB(Color color) {
        int[] rgb = new int[3];
        rgb[0] = (int) (color.getRed() * 255);
        rgb[1] = (int) (color.getGreen() * 255);
        rgb[2] = (int) (color.getBlue() * 255);
        return rgb;
    }

    // @requires colour[0] >= 0 && colour[0] <= 255 && colour[1] >= 0 && colour[1] <= 255 && colour[2] >= 0 && colour[2] <= 255
    private void setSpectrumIndicatorColor(Pane indicator, int[] colour) {
        if (colour[0] < 0 || colour[0] > 255 || colour[1] < 0 || colour[1] > 255 || colour[2] < 0 || colour[2] > 255) {
            throw new IllegalArgumentException();
        }
        (((StackPane) indicator.getChildren().get(0)).getChildren().get(1)).setStyle("-fx-fill: rgb(" + colour[0] + "," + colour[1] + "," + colour[2] + ");");
    }
}
