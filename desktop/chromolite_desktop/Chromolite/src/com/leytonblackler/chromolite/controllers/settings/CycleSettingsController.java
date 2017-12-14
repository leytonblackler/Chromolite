package com.leytonblackler.chromolite.controllers.settings;

import com.leytonblackler.chromolite.controllers.Controller;
import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.main.effecthandler.effects.CycleEffect;
import com.leytonblackler.chromolite.main.settings.SettingsManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class CycleSettingsController implements Controller, Initializable {

    private static final int DEFAULT_BRIGHTNESS = 100;
    private static final int DEFAULT_SPEED = 50;

    @FXML
    private Slider brightnessSlider;

    @FXML
    private Label brightnessPercentLabel;

    @FXML
    private Slider speedSlider;

    @FXML
    private Label speedPercentLabel;

    @FXML
    private ChoiceBox<String> numberOfColoursChoiceBox;

    @FXML
    private ChoiceBox<String> transitionChoiceBox;

    /*@FXML
    private CheckBox syncWithRazerCheckBox;*/

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        initialiseChoiceBox(numberOfColoursChoiceBox, CycleEffect.NumberOfColours.values(), CycleEffect.NumberOfColours.SPECTRUM);

        initialiseChoiceBox(transitionChoiceBox, CycleEffect.Transition.values(), CycleEffect.Transition.BLEND);

        //initialiseChoiceBox(directionChoiceBox, Directions.values(), Directions.LEFT);

        brightnessSlider.setValue(DEFAULT_BRIGHTNESS);
        brightnessPercentLabel.setText(Integer.toString(DEFAULT_BRIGHTNESS) + "%");
        brightnessSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                int percentValue = (int) Math.round(new_val.doubleValue());
                String percentString = Long.toString(percentValue) + "%";
                brightnessPercentLabel.setText(percentString);
            }
        });

        speedSlider.setValue(DEFAULT_SPEED);
        speedPercentLabel.setText(Integer.toString(DEFAULT_SPEED) + "%");
        speedSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                int percentValue = (int) Math.round(new_val.doubleValue());
                //Ensure that the percent value is not 0.
                if (percentValue == 0) {
                    percentValue = 1;
                }
                String percentString = Long.toString(percentValue) + "%";
                speedPercentLabel.setText(percentString);
                Chromolite.getInstance().getSettings().setSpeed(percentValue);
            }
        });
    }

    @Override
    public void update(SettingsManager settings) {
        //
    }

    private void initialiseChoiceBox(ChoiceBox choiceBox, Enum[] choices, Enum defaultChoice) {
        ObservableList<String> choiceStrings = FXCollections.observableArrayList();
        for (Enum choice : choices) {
            choiceStrings.add(choice.toString());
        }
        choiceBox.setItems(choiceStrings);
        choiceBox.setValue(defaultChoice.toString());
    }
}
