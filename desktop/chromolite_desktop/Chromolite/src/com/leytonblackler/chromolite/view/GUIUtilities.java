package com.leytonblackler.chromolite.view;

import com.leytonblackler.chromolite.controllers.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;

public class GUIUtilities {

    /**
     * Loads a node from an FXML file and adds it as a child to an existing pane.
     *
     * @param pane          The parent pane to add the loaded node to (optional).
     * @param pathToFXML    The file path to the FXML file to load.
     * @return
     */
    public static Pair<Pane, Controller> loadFXMLPane(Pane pane, String pathToFXML) {
        FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemClassLoader().getResource(pathToFXML));
        try {
            Node node = fxmlLoader.load();
            if (pane != null) {
                pane.getChildren().add(node);
            }
            Controller controller = fxmlLoader.getController();
            return new Pair<>((Pane) node, controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(pathToFXML);
        throw new IllegalStateException();
    }

    /**
     * Creates a title bar for the window which allows the window to be dragged, closed and minimised.
     * @return The title bar in the form of a Pane.
     */
    public static Pane createTitleBar(String pathToImage) {
        //Create a pane for the title bar, where the image and buttons will be stacked.
        StackPane bar = new StackPane();
        bar.setPrefHeight(2 * Constants.PADDING.getValue());

        //If an image path was given, load and add the image to the title bar.
        if (pathToImage != null) {
            //Load the given image.
            ImageView image = new ImageView();
            image.setImage(new Image(ClassLoader.getSystemClassLoader().getResource(pathToImage).toExternalForm()));
            image.setPreserveRatio(true);
            //Create a box to use as a container for the image.
            HBox imageBox = new HBox();
            imageBox.setAlignment(Pos.CENTER);
            imageBox.getChildren().add(image);
            //Add the image box containing the image to the title bar.
            bar.getChildren().add(imageBox);
        }
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
     * Creates a button that allows the window it is contained within to be minimised.
     * @param size The width and height of the button.
     * @return A button that allows the window it is contained within to be minimised.
     */
    public static Button createMinimiseButton(double size) {
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
     * Creates a button that allows the window it is contained within to be closed.
     * @param size The width and height of the button.
     * @return A button that allows the window it is contained within to be closed.
     */
    public static Button createCloseButton(double size) {
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

    public static void addTitle(Pane pane, String title) {
        HBox titleBox = new HBox();
        titleBox.setAlignment(Pos.CENTER_LEFT);
        Label label = new Label(title);
        label.setId("section-title-label");
        label.translateYProperty().setValue(15 / 2); //FXML PAD VALUE USED HERE, NEEDS DYNAMIC SOLUTION
        titleBox.getChildren().add(label);
        pane.getChildren().add(titleBox);
    }

    public static void initialiseChoiceBox(ChoiceBox choiceBox, Enum[] choices, Enum defaultChoice) {
        ObservableList<String> choiceStrings = FXCollections.observableArrayList();
        for (Enum choice : choices) {
            choiceStrings.add(choice.toString());
        }
        choiceBox.setItems(choiceStrings);
        choiceBox.setValue(defaultChoice.toString());
    }

}
