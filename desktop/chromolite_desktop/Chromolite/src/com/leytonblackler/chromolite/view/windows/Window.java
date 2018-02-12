package com.leytonblackler.chromolite.view.windows;

import com.leytonblackler.chromolite.Chromolite;
import com.leytonblackler.chromolite.controllers.LEDStripSimulationController;
import com.leytonblackler.chromolite.main.settings.SettingsObserver;
import com.leytonblackler.chromolite.view.GUIUtilities;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public abstract class Window extends SettingsObserver {

    private Stage stage;

    private Pane titleBar;
    private double xOffset;
    private double yOffset;

    /**
     * Creates a new window without supplying a title bar image.
     * @param stage
     */
    public Window(Stage stage) {
        this(stage, null);
    }

    public Window(Stage stage, String pathToImage) {
        this.stage = stage;

        Scene scene;

        //Create the scene root pane.
        VBox root = new VBox();
        root.setId("root-pane");
        //Create the window title bar.
        titleBar = GUIUtilities.createTitleBar(pathToImage);
        enableWindowDragging(titleBar);
        root.getChildren().add(titleBar);
        root.getChildren().add(createSceneContents());

        //Create a scene with a shadow if the operating system is Windows, since unlike macOS
        //and Linux, Windows does not apply a shadow to undecorated windows.
        String osName = System.getProperty("os.name");
        if (osName != null && osName.startsWith("Windows")) {
            scene = createShadowedScene(root);
            stage.initStyle(StageStyle.TRANSPARENT);

        } else {
            scene = new Scene(root);
            stage.initStyle(StageStyle.UNDECORATED);
        }

        //Apply the CSS styling to the scene (window contents).
        scene.getStylesheets().add(getClass().getClassLoader().getResource("view/Style.css").toExternalForm());

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

    protected abstract Parent createSceneContents();

}
