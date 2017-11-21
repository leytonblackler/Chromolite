package com.leytonblackler.chromolite.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public ImageView logo;

    @FXML
    public ImageView spectrum;

    @FXML
    public ImageView razerLogo;
    private Image[] razerLogoImages = new Image[2];

    @FXML
    private Rectangle r1;

    @FXML
    private Rectangle r2;

    @FXML
    private Rectangle r3;

    @FXML
    private ToggleButton razerButton;

    @FXML
    private ToggleButton staticButton;

    @FXML
    private ToggleButton randomButton;

    @FXML
    private Slider speedSlider;

    @FXML
    private Label speedPercent;

    @FXML
    private Pane primarySpectrumIndicator, secondarySpectrumIndicator, tertiarySpectrumIndicator;
    private Pane[] spectrumIndicators = new Pane[3];

    @FXML
    private StackPane spectrumStackPane;

    private boolean mouseOverSpectrum = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logo.setImage(new Image(getClass().getClassLoader().getResource("images/logo.png").toExternalForm()));
        spectrum.setImage(new Image(getClass().getClassLoader().getResource("images/spectrum.png").toExternalForm()));
        razerLogoImages[0] = new Image(getClass().getClassLoader().getResource("images/razer_logo_light.png").toExternalForm());
        razerLogoImages[1] = new Image(getClass().getClassLoader().getResource("images/razer_logo_dark.png").toExternalForm());
        razerLogo.setImage(razerLogoImages[0]);

        spectrumIndicators[0] = primarySpectrumIndicator;
        spectrumIndicators[1] = secondarySpectrumIndicator;
        spectrumIndicators[2] = tertiarySpectrumIndicator;

        for (Pane spectrumIndicator : spectrumIndicators) {
            spectrumIndicator.translateXProperty().unbind();
            spectrumIndicator.translateYProperty().unbind();
        }

        r1.setStyle("-fx-fill: red;");
        r2.setStyle("-fx-fill: #00ff00;");
        r3.setStyle("-fx-fill: blue;");

        speedSlider.setValue(50);
        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                Long percentValue = Math.round(new_val.doubleValue());
                String percentString = Long.toString(percentValue) + "%";
                speedPercent.setText(percentString);
            }
        });

        EventHandler<MouseEvent> spectrumMouseHandler =
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent t) {
                        if (t.getX() > 0 && t.getX() < spectrumStackPane.getWidth()) {
                            spectrumIndicators[1].translateXProperty().setValue(t.getX());
                        }
                        if (t.getY() > 0 && t.getY() < spectrumStackPane.getHeight()) {
                            spectrumIndicators[1].translateYProperty().setValue(t.getY());
                        }
                    }
                };

        spectrumStackPane.setOnMousePressed(spectrumMouseHandler);
        spectrumStackPane.setOnMouseDragged(spectrumMouseHandler);

        String fontPath = getClass().getClassLoader().getResource("fonts/Roboto-Bold.ttf").toExternalForm();
        fontPath = fontPath.replaceAll("%20", " ");
        Font.loadFont(fontPath, 10);
    }

    @FXML
    public void staticButtonClicked() {
        System.out.println("static");
        staticButton.setSelected(true);
        randomButton.setSelected(false);
    }

    @FXML
    public void randomButtonClicked() {
        //
        System.out.println("random");
        randomButton.setSelected(true);
        staticButton.setSelected(false);
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
}