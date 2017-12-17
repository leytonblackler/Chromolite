package com.leytonblackler.chromolite.controllers.settings;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.controllers.Controller;
import com.leytonblackler.chromolite.main.effecthandler.effects.StaticEffect;
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

public class StaticSettingsController implements Controller, Initializable {

    private static final int DEFAULT_BRIGHTNESS = 100;

    @FXML
    private Slider brightnessSlider;

    @FXML
    private Label brightnessPercentLabel;

    @FXML
    private ChoiceBox<String> styleChoiceBox;

    @FXML
    private ChoiceBox<String> numberOfColoursChoiceBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initialiseChoiceBox(styleChoiceBox, StaticEffect.Style.values(), StaticEffect.Style.SOLID);
        initialiseChoiceBox(numberOfColoursChoiceBox, StaticEffect.NumberOfColours.values(), StaticEffect.NumberOfColours.ONE);

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
    }

    @Override
    public void update(SettingsManager settings) {
        numberOfColoursChoiceBox.setValue(settings.getStaticNumberOfColours().toString());
        System.out.println(settings.getStaticNumberOfColours() + " " + settings.getStaticStyle());
    }

    private void initialiseChoiceBox(ChoiceBox choiceBox, Enum[] choices, Enum defaultChoice) {
        ObservableList<String> choiceStrings = FXCollections.observableArrayList();
        for (Enum choice : choices) {
            choiceStrings.add(choice.toString());
        }
        choiceBox.setItems(choiceStrings);
        choiceBox.setValue(defaultChoice.toString());
    }

    @FXML
    private void styleChoiceBoxChanged() {
        Chromolite.getInstance().getSettings().setStaticStyle(StaticEffect.Style.valueOf(styleChoiceBox.getValue()));
    }

    @FXML
    private void numberOfColoursChoiceBoxChanged() {
        Chromolite.getInstance().getSettings().setStaticNumberOfColours(StaticEffect.NumberOfColours.valueOf(numberOfColoursChoiceBox.getValue()));
    }
}
