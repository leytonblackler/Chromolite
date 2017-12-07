package com.leytonblackler.chromolite.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Constants {

    public static DoubleProperty PADDING = new SimpleDoubleProperty(scale(15));
    public static DoubleProperty BUTTON_HEIGHT = new SimpleDoubleProperty(scale(2.5 * PADDING.doubleValue()));

    private static double scale(double value) {
        return GUI.SCALE * value;
    }
}
